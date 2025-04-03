package Controller;

import DAO.AutorDAO;
import DAO.QuestionarioDAO;
import VO.Questionario;
import VO.Autor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;

@WebServlet("/CadastrarQuestionarioServlet")
public class CadastrarQuestionarioServlet extends HttpServlet {

    // Método para exibir o formulário (GET)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Autor> listaAutores = AutorDAO.listarTodos(); // Busca todos os autores
        request.setAttribute("autores", listaAutores);
        
// Debug
System.out.println("DEBUG: Autores recuperados no servlet, total: " + listaAutores.size());
for (Autor autor : listaAutores) {
    System.out.println("DEBUG: Autor - ID: " + autor.getId() + ", Nome: " + autor.getNome());
}

        RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroQuestionario.jsp");
        dispatcher.forward(request, response);
    }

    // Método para processar o cadastro (POST)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String titulo = request.getParameter("titulo");
        String autorIdStr = request.getParameter("autorId");

        // Verifica se os dados são válidos
        if (titulo != null && !titulo.trim().isEmpty() && autorIdStr != null) {
            try {
                int autorId = Integer.parseInt(autorIdStr);

                // Cria o objeto Questionario
                Questionario questionario = new Questionario();
                questionario.setTitulo(titulo);
                questionario.setAutor(new Autor(autorId));  // Define o autor com base no ID

                // Persistir no banco de dados
                QuestionarioDAO dao = new QuestionarioDAO();
                dao.cadastrarQuestionario(questionario);

                // Redireciona para a página de sucesso ou listagem
                response.sendRedirect("sucesso.jsp");
            } catch (NumberFormatException e) {
                response.getWriter().println("Erro: ID do autor inválido.");
            }
        } else {
            response.getWriter().println("Dados inválidos!");
        }
    }
}
