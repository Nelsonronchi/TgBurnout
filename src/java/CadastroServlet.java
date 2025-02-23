import Conexao.DatabaseConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastroServlet extends HttpServlet {
    // Método doPost para processar o cadastro
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Obtém os parâmetros enviados pelo formulário
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String cpf = request.getParameter("cpf");
        String dataNascimento = request.getParameter("data_nascimento");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        String profissao = request.getParameter("profissao");
        String senha = request.getParameter("senha");
        String tipoUsuario = request.getParameter("tipo_usuario");
        
        // Conecta ao banco de dados e insere os dados na tabela 'usuarios'
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO usuarios (nome, email, cpf, data_nascimento, telefone, sexo, profissao, senha, tipo_usuario) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, cpf);
            stmt.setString(4, dataNascimento);
            stmt.setString(5, telefone);
            stmt.setString(6, sexo);
            stmt.setString(7, profissao);
            stmt.setString(8, senha);
            stmt.setString(9, tipoUsuario);
            
            stmt.executeUpdate();
            response.sendRedirect("login.html");  // Redireciona para a página de login após o cadastro
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao processar cadastro.");
        }
    }

    // Não é necessário definir um método processRequest se não for usado
    @Override
    public String getServletInfo() {
        return "Servlet de Cadastro de Usuário";
    }
}
