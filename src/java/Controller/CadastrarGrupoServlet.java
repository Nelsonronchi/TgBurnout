package Controller;

import DAO.GrupoDAO;
import VO.Grupo;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.List;

@WebServlet("/cadastrarGrupo")
public class CadastrarGrupoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Exibe o formulário de cadastro e lista existentes
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Listar os grupos já cadastrados
        List<Grupo> grupos = new GrupoDAO().listarTodos();
        request.setAttribute("grupos", grupos);
        RequestDispatcher rd = request.getRequestDispatcher("cadastrarGrupo.jsp");
        rd.forward(request, response);
    }

    // Processa o POST do formulário
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("nome");
        if (nome != null && !nome.trim().isEmpty()) {
            Grupo g = new Grupo();
            g.setNome(nome.trim());
            new GrupoDAO().cadastrar(g);
            response.sendRedirect("cadastrarGrupo"); // volta ao GET para ver lista atualizada
        } else {
            request.setAttribute("error", "O nome do grupo não pode ficar vazio.");
            doGet(request, response);
        }
    }
}
