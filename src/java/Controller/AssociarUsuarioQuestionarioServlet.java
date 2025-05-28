package Controller;

import DAO.QuestionarioDAO;
import DAO.UsuarioDAO;
import DAO.GrupoDAO;
import DAO.QuestionarioUsuarioDAO;
import VO.Questionario;
import VO.Usuario;
import VO.Grupo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.List;

@WebServlet("/associarUsuarioQuestionario")
public class AssociarUsuarioQuestionarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // exibe o formulário: busca questionarios, usuarios e grupos e encaminha ao JSP
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // carrega os dados via DAO
        List<Questionario> questionarios = new QuestionarioDAO().listarTodos();
        List<Usuario> usuarios       = new UsuarioDAO().listarTodos();
        List<Grupo> grupos           = new GrupoDAO().listarTodos();

        // coloca na request para o JSP
        request.setAttribute("questionarios", questionarios);
        request.setAttribute("usuarios", usuarios);
        request.setAttribute("grupos", grupos);

        // encaminha ao JSP
        RequestDispatcher rd = request.getRequestDispatcher("associarUsuarioQuestionario.jsp");
        rd.forward(request, response);
    }

    // processa o POST: associa e redireciona para uma página de sucesso
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tipo = request.getParameter("tipo"); // "individual" ou "grupo"
        int questionarioId = Integer.parseInt(request.getParameter("questionarioId"));
        QuestionarioUsuarioDAO dao = new QuestionarioUsuarioDAO();

        if ("individual".equals(tipo)) {
            int usuarioId = Integer.parseInt(request.getParameter("usuarioId"));
            dao.associarUsuarioAoQuestionario(usuarioId, questionarioId);
        } else {
            int grupoId = Integer.parseInt(request.getParameter("grupoId"));
            dao.associarGrupoAoQuestionario(grupoId, questionarioId);
        }

        // redireciona para página de sucesso (pode ser uma JSP simples)
        response.sendRedirect("sucessoAssociacao.jsp");
    }
}
