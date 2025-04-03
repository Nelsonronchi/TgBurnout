package DAO;

import Conexao.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuestionarioUsuarioDAO {

    // Método para associar um usuário a um questionário
    public void associarUsuarioAoQuestionario(int usuarioId, int questionarioId) {
        String sql = "INSERT INTO questionario_usuario (usuario_id, questionario_id) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);
            stmt.setInt(2, questionarioId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para associar um grupo de usuários a um questionário
    public void associarGrupoAoQuestionario(int grupoId, int questionarioId) {
        String sql = "INSERT INTO questionario_usuario (usuario_id, questionario_id) " +
                     "SELECT usuario_id, ? FROM grupo_usuario WHERE grupo_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, questionarioId);
            stmt.setInt(2, grupoId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
