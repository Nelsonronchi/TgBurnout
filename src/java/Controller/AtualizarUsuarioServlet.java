package Controller;

import DAO.PessoaDAO;
import VO.Pessoa;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AtualizarUsuarioServlet")
public class AtualizarUsuarioServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String telefone = request.getParameter("telefone");
        String profissao = request.getParameter("profissao");

        Pessoa pessoa = new Pessoa(id, nome, email, telefone, profissao);
        PessoaDAO pessoaDAO = new PessoaDAO();
        pessoaDAO.atualizarUsuario(pessoa);

        response.sendRedirect("ListarUsuariosServlet");
    }
}
