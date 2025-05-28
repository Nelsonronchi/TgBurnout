package Controller;

import DAO.UsuarioQuestionarioDAO;
import java.io.IOException;
import java.util.List;
import VO.UsuarioQuestionario;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ListarMeusQuestionariosServlet")
public class ListarMeusQuestionariosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("=== Servlet ListarMeusQuestionariosServlet foi chamado ===");

        HttpSession session = request.getSession(false);

        if (session != null) {
            String cpf = (String) session.getAttribute("cpf");

            if (cpf != null) {
                System.out.println("CPF na sessão: " + cpf);

                // Paginação
                int page = 1;
                int recordsPerPage = 5;

                String pageStr = request.getParameter("page");
                if (pageStr != null) {
                    try {
                        page = Integer.parseInt(pageStr);
                    } catch (NumberFormatException e) {
                        page = 1;
                    }
                }

                UsuarioQuestionarioDAO dao = new UsuarioQuestionarioDAO();

                // Lista paginada
                List<UsuarioQuestionario> lista = dao.listarPorCpfComPaginacao(cpf, (page - 1) * recordsPerPage, recordsPerPage);
                int totalRecords = dao.contarPorCpf(cpf);

                int noOfPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);

                System.out.println("[DEBUG] Lista retornada do DAO: " + (lista != null ? lista.size() : "null"));
                System.out.println("[DEBUG] Total de registros: " + totalRecords);
                System.out.println("[DEBUG] Número total de páginas: " + noOfPages);
                System.out.println("[DEBUG] Página atual: " + page);

                request.setAttribute("meusQuestionarios", lista);
                request.setAttribute("noOfPages", noOfPages);
                request.setAttribute("currentPage", page);
            } else {
                System.out.println("[ERRO] CPF não encontrado na sessão.");
            }
        } else {
            System.out.println("[ERRO] Sessão inexistente.");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("meusQuestionarios.jsp");
        dispatcher.forward(request, response);
    }
}
