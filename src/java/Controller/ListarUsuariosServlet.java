package Controller;

import DAO.PessoaDAO;
import VO.Pessoa;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ListarUsuariosServlet")
public class ListarUsuariosServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PessoaDAO pessoaDAO = new PessoaDAO();
        String nomePesquisa = request.getParameter("nomePesquisa");
        
        List<Pessoa> listaUsuarios;
        
        if (nomePesquisa != null && !nomePesquisa.trim().isEmpty()) {
            listaUsuarios = pessoaDAO.buscarPorNome(nomePesquisa);
        } else {
            listaUsuarios = pessoaDAO.listarUsuarios();
        }

        request.setAttribute("usuarios", listaUsuarios);
        request.getRequestDispatcher("listarUsuarios.jsp").forward(request, response);
    }
}
