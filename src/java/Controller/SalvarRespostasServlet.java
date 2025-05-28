package Controller;

import DAO.RespostaDAO;
import VO.Resposta;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;

@WebServlet("/salvarRespostas")
public class SalvarRespostasServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recebe o id do questionário
        String questionarioIdStr = request.getParameter("questionarioId");
        if (questionarioIdStr == null) {
            response.getWriter().println("Erro: questionarioId não encontrado.");
            return;
        }
        int questionarioId = Integer.parseInt(questionarioIdStr);

        // Pega o usuário da sessão
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioId") == null) {
            response.sendRedirect("login.jsp"); // ou outra página de login
            return;
        }
        int usuarioId = (int) session.getAttribute("usuarioId");
        String cpfUsuario = (String) session.getAttribute("cpf");

        // Instancia DAO para salvar respostas
        RespostaDAO dao = new RespostaDAO();

        // Percorre os parâmetros para encontrar respostas
        Map<String, String[]> parametros = request.getParameterMap();

        try {
            for (String param : parametros.keySet()) {
                if (param.startsWith("resposta_")) {
                    String questaoIdStr = param.substring("resposta_".length());
                    int questaoId = Integer.parseInt(questaoIdStr);

                    String valorRespostaStr = request.getParameter(param);
                    int valorResposta = Integer.parseInt(valorRespostaStr);

                    // Cria objeto Resposta
                    Resposta r = new Resposta();
                    r.setUsuarioId(usuarioId);
                    r.setQuestionarioId(questionarioId);
                    r.setQuestaoId(questaoId);
                    r.setValorResposta(valorResposta);
                    r.setCpfUsuario(cpfUsuario);

                    dao.salvar(r);
                }
            }
            // Após salvar todas, redireciona para listar questionários
            response.sendRedirect("ListarMeusQuestionariosServlet");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Erro ao salvar respostas: " + e.getMessage());
        }
    }
}
