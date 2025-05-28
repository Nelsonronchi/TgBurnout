package DAO;

import Conexao.DatabaseConnection;
import VO.GrupoQuestionario;
import VO.Grupo;
import VO.Questionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GrupoQuestionarioAssociationDAO {

    /** Lista todas as associações de um grupo, com título do questionário */
    public List<GrupoQuestionario> listarPorGrupo(int grupoId) {
        List<GrupoQuestionario> lista = new ArrayList<>();
        String sql =
            "SELECT gq.id, q.id AS qid, q.titulo AS qtitulo " +
            "FROM grupos_questionarios gq " +
            "JOIN questionarios q ON gq.questionario_id = q.id " +
            "WHERE gq.grupo_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, grupoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    GrupoQuestionario gq = new GrupoQuestionario();
                    gq.setId(rs.getInt("id"));

                    Grupo g = new Grupo();
                    g.setId(grupoId);
                    gq.setGrupo(g);

                    Questionario q = new Questionario();
                    q.setId(rs.getInt("qid"));
                    q.setTitulo(rs.getString("qtitulo"));
                    gq.setQuestionario(q);

                    lista.add(gq);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /** Deleta uma associação pelo seu id */
    public void deletarPorId(int assocId) {
        String sql = "DELETE FROM grupos_questionarios WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, assocId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
