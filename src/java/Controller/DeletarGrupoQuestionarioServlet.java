package Controller;

import DAO.GrupoQuestionarioAssociationDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/deletarGrupoQuestionario")
public class DeletarGrupoQuestionarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private int parseParam(HttpServletRequest req, String name) {
        String v = req.getParameter(name);
        try { return (v != null && !v.isEmpty()) ? Integer.parseInt(v) : 0; }
        catch (NumberFormatException e) { return 0; }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int assocId = parseParam(request, "id");
        int grupoId = parseParam(request, "grupoId");

        new GrupoQuestionarioAssociationDAO().deletarPorId(assocId);

        response.sendRedirect("listarGrupoQuestionario?grupoId=" + grupoId);
    }
}
