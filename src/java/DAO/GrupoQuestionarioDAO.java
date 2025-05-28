package DAO;

import Conexao.DatabaseConnection;
import VO.GrupoQuestionario;
import VO.Grupo;
import VO.Questionario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GrupoQuestionarioDAO {

    // Associa um grupo a um questionário
    public void associarGrupoAoQuestionario(int grupoId, int questionarioId) {
        String sql = "INSERT INTO grupos_questionarios (grupo_id, questionario_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, grupoId);
            stmt.setInt(2, questionarioId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lista todas as associações de um grupo
    public List<GrupoQuestionario> listarPorGrupo(int grupoId) {
        List<GrupoQuestionario> lista = new ArrayList<>();
        String sql = "SELECT id, questionario_id FROM grupos_questionarios WHERE grupo_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, grupoId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                GrupoQuestionario gq = new GrupoQuestionario();
                gq.setId(rs.getInt("id"));

                Grupo g = new Grupo();
                g.setId(grupoId);
                gq.setGrupo(g);

                Questionario q = new Questionario();
                q.setId(rs.getInt("questionario_id"));
                gq.setQuestionario(q);

                lista.add(gq);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Remove associação pelo id da associação
    public void deletarPorGrupoQuestionario(int assocId) {
        String sql = "DELETE FROM grupos_questionarios WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, assocId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Remove todas as associações de um grupo
    public void deletarPorGrupo(int grupoId) {
        String sql = "DELETE FROM grupos_questionarios WHERE grupo_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, grupoId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Remove todas as associações de um questionário
    public void deletarPorQuestionario(int questionarioId) {
        String sql = "DELETE FROM grupos_questionarios WHERE questionario_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, questionarioId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
