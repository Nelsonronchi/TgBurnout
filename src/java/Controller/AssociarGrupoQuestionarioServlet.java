package Controller;

import DAO.GrupoDAO;
import DAO.QuestionarioDAO;
import DAO.GrupoQuestionarioDAO;
import VO.Grupo;
import VO.Questionario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.List;

@WebServlet("/associarGrupoQuestionario")
public class AssociarGrupoQuestionarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Grupo> grupos = new GrupoDAO().listarTodos();
        List<Questionario> questionarios = new QuestionarioDAO().listarTodos();
        request.setAttribute("grupos", grupos);
        request.setAttribute("questionarios", questionarios);
        RequestDispatcher rd = request.getRequestDispatcher("associarGrupoQuestionario.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int grupoId = Integer.parseInt(request.getParameter("grupoId"));
        int questionarioId = Integer.parseInt(request.getParameter("questionarioId"));
        new GrupoQuestionarioDAO().associarGrupoAoQuestionario(grupoId, questionarioId);
        response.sendRedirect("associarGrupoQuestionario");
    }
}