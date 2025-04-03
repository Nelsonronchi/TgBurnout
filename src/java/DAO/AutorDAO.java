package DAO;

import VO.Autor;
import Conexao.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class AutorDAO {
public boolean cadastrarAutor(Autor autor) {
    String sql = "INSERT INTO autores (nome, descricao) VALUES (?, ?)";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        System.out.println("Inserindo no banco: Nome = " + autor.getNome() + ", Descrição = " + autor.getDescricao());

        stmt.setString(1, autor.getNome());
        stmt.setString(2, autor.getDescricao());

        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }      
}
public static List<Autor> listarTodos() {
    List<Autor> listaAutores = new ArrayList<>();
    
    String sql = "SELECT id, nome FROM autores"; // Verifique se o nome da tabela está correto!
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Autor autor = new Autor();
            autor.setId(rs.getInt("id"));  // <-- AQUI DEFINE O ID CORRETAMENTE
            autor.setNome(rs.getString("nome"));
            listaAutores.add(autor);

            // Debug
            System.out.println("DEBUG: Autor encontrado -> ID: " + autor.getId() + ", Nome: " + autor.getNome());
        }

    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Erro ao listar autores: " + e.getMessage());
    }

    // Debug final para verificar se a lista tem elementos
    System.out.println("DEBUG: Total de autores carregados: " + listaAutores.size());
    return listaAutores;
}


}
