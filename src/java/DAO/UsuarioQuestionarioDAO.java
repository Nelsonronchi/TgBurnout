package DAO;

import Conexao.DatabaseConnection;
import VO.Usuario;
import VO.Questionario;
import VO.UsuarioQuestionario;

import java.sql.*;
import java.util.*;

public class UsuarioQuestionarioDAO {

    public List<UsuarioQuestionario> listarPorUsuario(int usuarioId) {
        List<UsuarioQuestionario> lista = new ArrayList<>();

        String sql = "SELECT uq.id, q.id AS qid, q.titulo " +
                     "FROM usuarios_questionarios uq " +
                     "JOIN questionarios q ON uq.questionario_id = q.id " +
                     "WHERE uq.usuario_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    UsuarioQuestionario uq = new UsuarioQuestionario();
                    uq.setId(rs.getInt("id"));

                    Usuario u = new Usuario();
                    u.setId(usuarioId);
                    uq.setUsuario(u);

                    Questionario q = new Questionario();
                    q.setId(rs.getInt("qid"));
                    q.setTitulo(rs.getString("titulo"));
                    uq.setQuestionario(q);

                    lista.add(uq);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    public List<UsuarioQuestionario> listarPorCpf(String cpf) {
        List<UsuarioQuestionario> lista = new ArrayList<>();

        String sql = "SELECT uq.id, u.cpf, q.id AS questionario_id, q.titulo " +
                     "FROM usuarios_questionarios uq " +
                     "JOIN usuarios u ON uq.usuario_id = u.id " +
                     "JOIN questionarios q ON uq.questionario_id = q.id " +
                     "WHERE u.cpf = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setCpf(rs.getString("cpf"));

                Questionario questionario = new Questionario();
                questionario.setId(rs.getInt("questionario_id"));
                questionario.setTitulo(rs.getString("titulo"));

                UsuarioQuestionario uq = new UsuarioQuestionario();
                uq.setId(rs.getInt("id"));
                uq.setUsuario(usuario);
                uq.setQuestionario(questionario);

                lista.add(uq);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    
  public List<UsuarioQuestionario> listarPorCpfComPaginacao(String cpf, int offset, int limit) {
    List<UsuarioQuestionario> lista = new ArrayList<>();
    String sql = "SELECT uq.*, q.id AS id_questionario, q.titulo " +
                 "FROM usuarios_questionarios uq " +
                 "JOIN questionarios q ON uq.questionario_id = q.id " +
                 "JOIN usuarios u ON uq.usuario_id = u.id " +
                 "WHERE u.cpf = ? " +
                 "LIMIT ? OFFSET ?;";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, cpf);
        ps.setInt(2, limit);
        ps.setInt(3, offset);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                    UsuarioQuestionario uq = new UsuarioQuestionario();
                    uq.setId(rs.getInt("id"));

                    Questionario questionario = new Questionario();
                    questionario.setId(rs.getInt("id_questionario"));
                    questionario.setTitulo(rs.getString("titulo"));

                    uq.setQuestionario(questionario);

                    lista.add(uq);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return lista;
}


    // Conta total de questionários
    public int contarPorCpf(String cpf) {
        String sql = "SELECT COUNT(*) AS total " +
             "FROM usuarios_questionarios uq " +
             "JOIN usuarios u ON uq.usuario_id = u.id " +
             "WHERE u.cpf = ?;";

        int count = 0;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cpf);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }
}
