package Controller;

import DAO.UsuarioQuestionarioListarDAO;
import DAO.GrupoDAO;
import DAO.QuestionarioDAO;
import VO.UsuarioQuestionario;
import VO.Grupo;
import VO.Questionario;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/listarUsuarioQuestionario")
public class ListarUsuarioQuestionarioServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("=== Servlet ListarUsuarioQuestionarioServlet foi chamado ===");
        int grupoId = request.getParameter("grupoId") != null ? Integer.parseInt(request.getParameter("grupoId")) : 0;
        int questionarioId = request.getParameter("questionarioId") != null ? Integer.parseInt(request.getParameter("questionarioId")) : 0;

        List<Grupo> grupos = new GrupoDAO().listarTodos();
        List<Questionario> questionarios = new QuestionarioDAO().listarTodos();

        request.setAttribute("grupos", grupos);
        request.setAttribute("questionarios", questionarios);

        if (grupoId > 0 && questionarioId > 0) {
            List<UsuarioQuestionario> associacoes = new UsuarioQuestionarioListarDAO().listarPorGrupoEQuestionario(grupoId, questionarioId);
            request.setAttribute("associacoes", associacoes);
        }

        request.getRequestDispatcher("listarUsuarioQuestionario.jsp").forward(request, response);
    }
}
