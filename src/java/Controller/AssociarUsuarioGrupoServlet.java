package Controller;

import DAO.GrupoDAO;
import DAO.UsuarioDAO;
import DAO.UsuarioGrupoDAO;
import VO.Grupo;
import VO.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.List;

@WebServlet("/associarUsuarioGrupo")
public class AssociarUsuarioGrupoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Grupo> grupos = new GrupoDAO().listarTodos();
        List<Usuario> usuarios = new UsuarioDAO().listarTodos();
        request.setAttribute("grupos", grupos);
        request.setAttribute("usuarios", usuarios);
        RequestDispatcher rd = request.getRequestDispatcher("associarUsuarioGrupo.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int grupoId = Integer.parseInt(request.getParameter("grupoId"));
        String[] usuarioIds = request.getParameterValues("usuarioId");
        UsuarioGrupoDAO dao = new UsuarioGrupoDAO();
        for (String uid : usuarioIds) {
            dao.associarUsuarioAoGrupo(Integer.parseInt(uid), grupoId);
        }
        response.sendRedirect("associarUsuarioGrupo");
    }
}
