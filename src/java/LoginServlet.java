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
    // Método doPost para processar o login
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Obtém os parâmetros enviados pelo formulário de login
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM usuarios WHERE cpf = ? AND senha = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            
            // Verifica se o usuário foi encontrado no banco de dados
            if (rs.next()) {
                String tipoUsuario = rs.getString("tipo_usuario");
                HttpSession session = request.getSession();
                session.setAttribute("cpf", cpf);
                session.setAttribute("tipo_usuario", tipoUsuario);

                // Redireciona o usuário para a página correta de acordo com o tipo
                if ("Administrador".equals(tipoUsuario)) {
                    response.sendRedirect("ADMHome.html");
                } else {
                    response.sendRedirect("UsuarioHome.html");
                }
            } else {
                // Caso o login falhe, redireciona para a página de login com erro
                response.sendRedirect("login.html?erro=1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Em caso de erro no servidor, redireciona para a página de login com erro
            response.sendRedirect("login.html?erro=2");
        }
    }

    // Método para obter a descrição do servlet
    @Override
    public String getServletInfo() {
        return "Servlet de Login para autenticação de usuários";
    }
}
