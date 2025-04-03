package Controller;

import DAO.QuestaoDAO;
import DAO.QuestionarioDAO;
import DAO.AutorDAO;
import VO.Questao;
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

@WebServlet("/CadastrarQuestaoServlet")
public class CadastrarQuestaoServlet extends HttpServlet {

    // Método para exibir o formulário de cadastro (GET)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Busca todos os questionários e autores
        QuestionarioDAO questionarioDAO = new QuestionarioDAO(); // Criar instância
        List<Questionario> questionarios = questionarioDAO.listarTodos(); // Chamar método corretamente

        AutorDAO autorDAO = new AutorDAO(); // Criar instância
        List<Autor> autores = autorDAO.listarTodos();

        
        // Passa os dados para o JSP
        request.setAttribute("questionarios", questionarios);
        request.setAttribute("autores", autores);

        // Debug
        System.out.println("DEBUG: Questionários carregados: " + questionarios.size());
        System.out.println("DEBUG: Autores carregados: " + autores.size());

        // Redireciona para a página JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("cadastrarQuestao.jsp");
        dispatcher.forward(request, response);
    }

    // Método para processar o cadastro da questão (POST)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String texto = request.getParameter("texto");
        String questionarioIdStr = request.getParameter("questionarioId");
        String autorIdStr = request.getParameter("autorId");

        // Verifica se os dados são válidos
        if (texto != null && !texto.trim().isEmpty() && questionarioIdStr != null && autorIdStr != null) {
            try {
                int questionarioId = Integer.parseInt(questionarioIdStr);
                int autorId = Integer.parseInt(autorIdStr);

                // Cria o objeto Questao
                Questao questao = new Questao();
                questao.setTexto(texto);

                // Cria e define o questionário e o autor com base nos IDs
                Questionario questionario = new Questionario();
                questionario.setId(questionarioId);
                questao.setQuestionario(questionario);

                Autor autor = new Autor();
                autor.setId(autorId);
                questao.setAutor(autor);

                // Persistir no banco de dados
                QuestaoDAO dao = new QuestaoDAO();
                dao.cadastrarQuestao(questao);

                // Redireciona para a página de sucesso ou listagem
                response.sendRedirect("sucesso.jsp");
            } catch (NumberFormatException e) {
                // Trata erro de formato de número
                request.setAttribute("errorMessage", "Erro: ID do questionário ou autor inválido.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("cadastrarQuestao.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            // Caso os dados sejam inválidos
            request.setAttribute("errorMessage", "Dados inválidos! Preencha todos os campos.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("cadastrarQuestao.jsp");
            dispatcher.forward(request, response);
        }
    }
}
