package Controller;

import DAO.QuestaoDAO;
import DAO.QuestionarioDAO;
import VO.Questao;
import VO.Questionario;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ListarQuestoesServlet")
public class ListarQuestoesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String questionarioIdParam = request.getParameter("questionarioId");
        List<Questao> questoes;
        QuestionarioDAO questionarioDAO = new QuestionarioDAO();
        List<Questionario> questionarios = questionarioDAO.listarTodos();

        if (questionarioIdParam != null && !questionarioIdParam.isEmpty()) {
            int questionarioId = Integer.parseInt(questionarioIdParam);
            questoes = QuestaoDAO.listarPorQuestionario(questionarioId);
        } else {
            questoes = null; // Ainda não selecionou um questionário
        }

        request.setAttribute("questionarios", questionarios);
        request.setAttribute("questoes", questoes);

        RequestDispatcher dispatcher = request.getRequestDispatcher("listarQuestoes.jsp");
        dispatcher.forward(request, response);
    }
}
