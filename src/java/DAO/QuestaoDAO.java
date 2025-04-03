package DAO;

import Conexao.DatabaseConnection;
import VO.Questao;
import VO.Questionario;
import VO.Autor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestaoDAO {





 public void cadastrarQuestao(Questao questao) {
        String sql = "INSERT INTO questao (texto, questionario_id, autor_id) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, questao.getTexto());
            stmt.setInt(2, questao.getQuestionario().getId());  
            stmt.setInt(3, questao.getAutor().getId());  

            stmt.executeUpdate();
            System.out.println("Questão cadastrada com sucesso!");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

       public List<Questao> listarTodas() {
        List<Questao> questoes = new ArrayList<>();
        String sql = "SELECT q.id, q.texto, q.questionario_id, q.autor_id, a.nome AS autor_nome, qs.titulo AS questionario_titulo " +
                     "FROM questao q " +
                     "JOIN autores a ON q.autor_id = a.id " +
                     "JOIN questionarios qs ON q.questionario_id = qs.id";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String texto = rs.getString("texto");
                int autorId = rs.getInt("autor_id");
                String autorNome = rs.getString("autor_nome");
                int questionarioId = rs.getInt("questionario_id");
                String questionarioTitulo = rs.getString("questionario_titulo");

                // Criando o autor e questionário associados à questão
                Autor autor = new Autor();
                autor.setId(autorId);
                autor.setNome(autorNome);

                Questionario questionario = new Questionario();
                questionario.setId(questionarioId);
                questionario.setTitulo(questionarioTitulo);

                // Criando a questão
                Questao questao = new Questao(id, texto, questionario, autor);
                questoes.add(questao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questoes;
    }

    public Questao buscarPorId(int id) {
        Questao questao = null;
        String sql = "SELECT q.id, q.texto, q.questionario_id, q.autor_id, a.nome AS autor_nome, qs.titulo AS questionario_titulo " +
                     "FROM questao q " +
                     "JOIN autores a ON q.autor_id = a.id " +
                     "JOIN questionarios qs ON q.questionario_id = qs.id " +
                     "WHERE q.id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String texto = rs.getString("texto");
                int autorId = rs.getInt("autor_id");
                String autorNome = rs.getString("autor_nome");
                int questionarioId = rs.getInt("questionario_id");
                String questionarioTitulo = rs.getString("questionario_titulo");

                // Criando o autor e questionário associados à questão
                Autor autor = new Autor();
                autor.setId(autorId);
                autor.setNome(autorNome);

                Questionario questionario = new Questionario();
                questionario.setId(questionarioId);
                questionario.setTitulo(questionarioTitulo);

                // Criando a questão
                questao = new Questao(id, texto, questionario, autor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questao;
    }

    // Método para listar todos os questionários
    public static List<Questionario> listarTodos() {
        List<Questionario> questionarios = new ArrayList<>();
        String sql = "SELECT * FROM questionarios"; // Supondo que o nome da tabela seja 'questionarios'

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Questionario questionario = new Questionario();
                questionario.setId(rs.getInt("id"));  // Assumindo que 'id' é o nome da coluna
                questionario.setTitulo(rs.getString("titulo"));  // Assumindo que 'titulo' é o nome da coluna
                questionarios.add(questionario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questionarios;
    }
    
    
    public static List<Questao> listarPorQuestionario(int questionarioId) {
    List<Questao> questoes = new ArrayList<>();
    String sql = "SELECT * FROM questao WHERE questionario_id = ?";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, questionarioId);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            Questao q = new Questao();
            q.setId(rs.getInt("id"));
            q.setTexto(rs.getString("texto"));
            questoes.add(q);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return questoes;
}

public static void deletar(int id) {
    String sql = "DELETE FROM questao WHERE id = ?";
    
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
