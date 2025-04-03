package Controller;

import DAO.QuestionarioDAO;
import DAO.UsuarioDAO;
import DAO.GrupoDAO;
import VO.Questionario;
import VO.Usuario;
import VO.Grupo;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/carregarAssociacoes")
public class AssociarUsuarioQuestionarioServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Buscar todos os questionários, usuários e grupos no banco de dados
            List<Questionario> questionarios = new QuestionarioDAO().listarTodos();
            List<Usuario> usuarios = new UsuarioDAO().listarTodos();
            List<Grupo> grupos = new GrupoDAO().listarTodos();

            // Adicionar os dados na request para exibição na JSP
            request.setAttribute("questionarios", questionarios);
            request.setAttribute("usuarios", usuarios);
            request.setAttribute("grupos", grupos);

            // Encaminhar para a página JSP
            request.getRequestDispatcher("associarUsuarioQuestionario.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao carregar dados: " + e.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }
    }
}
