package Controller;

import Conexao.DatabaseConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Parâmetros do formulário
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM usuarios WHERE cpf = ? AND senha = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Dados do usuário
                int usuarioId = rs.getInt("id");
                String nome = rs.getString("nome");
                String tipoUsuario = rs.getString("tipo_usuario");
                String cpfBanco = rs.getString("cpf");
                
                 // Exibir no console
    System.out.println("=== USUÁRIO LOGADO ===");
    System.out.println("ID: " + usuarioId);
    System.out.println("CPF: " + cpfBanco);
    System.out.println("Senha: " + senha); // Somente para teste
    System.out.println("======================");
    
                // Salva os dados na sessão
                HttpSession session = request.getSession();
                session.setAttribute("cpf", cpfBanco);            // CPF
                session.setAttribute("usuarioId", usuarioId);     // ID do usuário
                session.setAttribute("nome", nome);               // Nome (opcional)
                session.setAttribute("tipo_usuario", tipoUsuario);// Tipo (usuário ou admin)

                // Redireciona conforme o tipo de usuário
                if ("Administrador".equals(tipoUsuario)) {
                    response.sendRedirect("ADMHome.html");
                } else {
                    response.sendRedirect("UsuarioHome.html");
                }

            } else {
                // Login inválido
                response.sendRedirect("login.html?erro=1");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("login.html?erro=2");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet de Login para autenticação de usuários";
    }
}
