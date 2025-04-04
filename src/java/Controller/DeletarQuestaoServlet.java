package Controller;

import DAO.QuestaoDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeletarQuestaoServlet")
public class DeletarQuestaoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int questaoId = Integer.parseInt(request.getParameter("questaoId"));
        QuestaoDAO.deletar(questaoId);

        response.sendRedirect("ListarQuestoesServlet");
    }
}
