package Controller;

import DAO.QuestaoDAO;
import DAO.QuestionarioDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeletarQuestionarioServlet")
public class DeletarQuestionarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        int questionarioId = Integer.parseInt(request.getParameter("questionarioId"));

        // Primeiro, deletamos as questões associadas ao questionário
        QuestaoDAO.deletarPorQuestionario(questionarioId);

        // Agora deletamos o próprio questionário
        QuestionarioDAO.deletar(questionarioId);

        response.sendRedirect("ListarQuestoesServlet");
    }
}
