package Controller;

import DAO.PessoaDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ExcluirUsuarioServlet")
public class ExcluirUsuarioServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        PessoaDAO pessoaDAO = new PessoaDAO();
        pessoaDAO.excluirUsuario(id);

        response.sendRedirect("ListarUsuariosServlet");
    }
}
