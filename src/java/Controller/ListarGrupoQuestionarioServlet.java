package Controller;

import DAO.GrupoDAO;
import DAO.GrupoQuestionarioAssociationDAO;
import VO.Grupo;
import VO.GrupoQuestionario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.List;

@WebServlet("/listarGrupoQuestionario")
public class ListarGrupoQuestionarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private int parseParam(HttpServletRequest req, String name) {
        String v = req.getParameter(name);
        try { return (v != null && !v.isEmpty()) ? Integer.parseInt(v) : 0; }
        catch (NumberFormatException e) { return 0; }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int grupoId = parseParam(request, "grupoId");

        List<Grupo> grupos = new GrupoDAO().listarTodos();
        request.setAttribute("grupos", grupos);
        request.setAttribute("selectedGrupoId", grupoId);

        List<GrupoQuestionario> associacoes = null;
        if (grupoId > 0) {
            associacoes = new GrupoQuestionarioAssociationDAO().listarPorGrupo(grupoId);
        }
        request.setAttribute("associacoes", associacoes);

        RequestDispatcher rd = request.getRequestDispatcher("listarGrupoQuestionario.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
