package Controller;

import DAO.PessoaDAO;
import VO.Pessoa;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditarUsuarioServlet")
public class EditarUsuarioServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        PessoaDAO pessoaDAO = new PessoaDAO();
        Pessoa pessoa = pessoaDAO.buscarPorId(id);

        request.setAttribute("pessoa", pessoa);
        request.getRequestDispatcher("editarUsuario.jsp").forward(request, response);
    }
}
