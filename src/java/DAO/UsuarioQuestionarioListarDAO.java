package DAO;

import Conexao.DatabaseConnection;
import VO.Usuario;
import VO.UsuarioQuestionario;
import VO.Questionario;
import Conexao.DatabaseConnection;

import java.sql.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;





public class UsuarioQuestionarioListarDAO {

    public List<UsuarioQuestionario> listarPorGrupoEQuestionario(int grupoId, int questionarioId) {
        List<UsuarioQuestionario> lista = new ArrayList<>();
        String sql = """
            SELECT uq.id, u.id AS usuario_id, u.nome, u.cpf
            FROM usuarios_questionarios uq
            JOIN usuarios u ON uq.usuario_id = u.id
            JOIN usuarios_grupos ug ON u.id = ug.usuario_id
            WHERE uq.questionario_id = ? AND ug.grupo_id = ?
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, questionarioId);
            stmt.setInt(2, grupoId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                UsuarioQuestionario uq = new UsuarioQuestionario();
                uq.setId(rs.getInt("id"));

                Usuario u = new Usuario();
                u.setId(rs.getInt("usuario_id"));
                u.setNome(rs.getString("nome"));
                uq.setUsuario(u);

                lista.add(uq);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
 public List<UsuarioQuestionario> listarPorCpf(String cpf) {
    List<UsuarioQuestionario> lista = new ArrayList<>();

    String sql = "SELECT uq.id AS uq_id, u.cpf, q.id AS questionario_id, q.titulo " +
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
            uq.setId(rs.getInt("uq_id"));
            uq.setUsuario(usuario);
            uq.setQuestionario(questionario);

            lista.add(uq);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return lista;
}

}  
    
    
    


