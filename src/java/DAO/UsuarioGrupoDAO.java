package DAO;

import Conexao.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioGrupoDAO {

    // Associa um usuário a um grupo
    public void associarUsuarioAoGrupo(int usuarioId, int grupoId) {
        String sql = "INSERT INTO usuarios_grupos (usuario_id, grupo_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            stmt.setInt(2, grupoId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Remove associação usuário-grupo
    public void deletarPorGrupo(int grupoId) {
        String sql = "DELETE FROM usuarios_grupos WHERE grupo_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, grupoId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarPorUsuario(int usuarioId) {
        String sql = "DELETE FROM usuarios_grupos WHERE usuario_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}