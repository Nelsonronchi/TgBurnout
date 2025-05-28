package DAO;

import VO.Grupo;
import Conexao.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GrupoDAO {

    
       // Cadastrar um novo grupo
    public void cadastrar(Grupo grupo) {
        String sql = "INSERT INTO grupos (nome) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, grupo.getNome());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Listar todos os grupos
    public List<Grupo> listarTodos() {
        List<Grupo> grupos = new ArrayList<>();
        String sql = "SELECT id, nome FROM grupos"; // Ajuste conforme sua tabela

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Grupo grupo = new Grupo();
                grupo.setId(rs.getInt("id"));
                grupo.setNome(rs.getString("nome"));
                grupos.add(grupo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return grupos;
    }

  
}
