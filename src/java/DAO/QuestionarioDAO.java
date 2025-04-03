package DAO;

import Conexao.DatabaseConnection;
import VO.Questionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class QuestionarioDAO {

    public List<Questionario> listarTodos() {
        List<Questionario> questionarios = new ArrayList<>();
        String sql = "SELECT id, titulo FROM questionarios"; // Ajuste conforme o nome da tabela no BD

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Questionario questionario = new Questionario();
                questionario.setId(rs.getInt("id"));
                questionario.setTitulo(rs.getString("titulo"));
                questionarios.add(questionario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questionarios;
    }

    public void cadastrarQuestionario(Questionario questionario) {
        String sql = "INSERT INTO questionarios (titulo, autor_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setString(1, questionario.getTitulo());
            ps.setInt(2, questionario.getAutor().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deletar(int id) {
    String sql = "DELETE FROM questionarios WHERE id = ?";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, id);
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
public static void deletarPorQuestionario(int questionarioId) {
    String sql = "DELETE FROM questao WHERE questionario_id = ?";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, questionarioId);
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}
