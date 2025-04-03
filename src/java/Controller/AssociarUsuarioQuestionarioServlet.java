package Controller;

import DAO.QuestionarioUsuarioDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/associarUsuarioQuestionario")
public class AssociarUsuarioQuestionarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
response.getWriter().println("Servlet funcionando!");
        String tipo = request.getParameter("tipo"); // "individual" ou "grupo"
        int questionarioId = Integer.parseInt(request.getParameter("questionarioId"));
        QuestionarioUsuarioDAO dao = new QuestionarioUsuarioDAO();

        if ("individual".equals(tipo)) {
            int usuarioId = Integer.parseInt(request.getParameter("usuarioId"));
            dao.associarUsuarioAoQuestionario(usuarioId, questionarioId);
        } else if ("grupo".equals(tipo)) {
            int grupoId = Integer.parseInt(request.getParameter("grupoId"));
            dao.associarGrupoAoQuestionario(grupoId, questionarioId);
        }

        response.sendRedirect("paginaDeSucesso.jsp"); // Redireciona após o cadastro
    }
}
