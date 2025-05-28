package DAO;

import VO.Pergunta;
import Conexao.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PerguntaDAO {

    public List<Pergunta> listarPorQuestionario(int questionarioId) {
        List<Pergunta> lista = new ArrayList<>();
        String sql = "SELECT id, texto FROM questao WHERE questionario_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, questionarioId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pergunta pergunta = new Pergunta();
                pergunta.setId(rs.getInt("id"));
                pergunta.setTexto(rs.getString("texto"));
                lista.add(pergunta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
