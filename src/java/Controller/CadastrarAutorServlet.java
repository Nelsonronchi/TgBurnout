package Controller;

import DAO.AutorDAO;
import VO.Autor;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CadastrarAutorServlet")
public class CadastrarAutorServlet extends HttpServlet {
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8"); // Garante que caracteres especiais sejam lidos corretamente

    String nome = request.getParameter("nomeAutor");
    String descricao = request.getParameter("descricaoAutor");

    System.out.println("Nome recebido: " + nome);
    System.out.println("Descrição recebida: " + descricao);

    if (nome == null || nome.trim().isEmpty()) {
        System.out.println("Erro: Nome não pode ser nulo ou vazio.");
        response.sendRedirect("erro.html");
        return;
    }

    if (descricao == null || descricao.trim().isEmpty()) {
        descricao = "Sem descrição";
    }

    Autor autor = new Autor();
    autor.setNome(nome);
    autor.setDescricao(descricao);

    System.out.println("Objeto Autor criado: " + autor.getNome() + ", " + autor.getDescricao());

    AutorDAO autorDAO = new AutorDAO();
    boolean sucesso = autorDAO.cadastrarAutor(autor);

    if (sucesso) {
        response.sendRedirect("sucessoAutor.jsp");
    } else {
        response.sendRedirect("erroAutor.jsp");
    }
}


}
