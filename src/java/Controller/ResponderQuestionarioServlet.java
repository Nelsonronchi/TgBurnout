package Controller;

import DAO.PerguntaDAO;
import DAO.QuestionarioDAO;
import VO.Pergunta;
import VO.Questionario;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/responderQuestionario")
public class ResponderQuestionarioServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int questionarioId = Integer.parseInt(request.getParameter("questionarioId"));

        Questionario questionario = new QuestionarioDAO().buscarPorId(questionarioId);
        List<Pergunta> perguntas = new PerguntaDAO().listarPorQuestionario(questionarioId);

        request.setAttribute("questionario", questionario);
        request.setAttribute("perguntas", perguntas);

        request.getRequestDispatcher("responderQuestionario.jsp").forward(request, response);
    }
}
