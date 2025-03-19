package DAO;

import VO.Pessoa;
import Conexao.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {
    
    // Logica do cadastrar usuario
    public boolean cadastrarPessoa(Pessoa pessoa) {
        // Validar o valor de sexo
        String sexo = pessoa.getSexo();
        if (!sexo.equals("M") && !sexo.equals("F") && !sexo.equals("O")) {
            System.out.println("Valor de sexo inválido. Deve ser 'M', 'F' ou 'O'.");
            return false;
        }
        
        // Validar o valor de profissao
        String profissao = pessoa.getProfissao();
        if (!profissao.equals("Medico") && !profissao.equals("Professor") && !profissao.equals("Dentista")
                && !profissao.equals("Engenheiro") && !profissao.equals("Advogado")) {
            System.out.println("Valor de profissão inválido.");
            return false;
        }
        
        // Validar o valor de tipo_usuario
        String tipoUsuario = pessoa.getTipoUsuario();
        if (!tipoUsuario.equals("Usuario") && !tipoUsuario.equals("Administrador")) {
            System.out.println("Valor de tipo_usuario inválido. Deve ser 'Usuario' ou 'Administrador'.");
            return false;
        }
        
        String sql = "INSERT INTO usuarios (nome, senha, email, cpf, data_nascimento, telefone, sexo, profissao, tipo_usuario) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getSenha());
            stmt.setString(3, pessoa.getEmail());
            stmt.setString(4, pessoa.getCpf());
            stmt.setString(5, pessoa.getDataNascimento());
            stmt.setString(6, pessoa.getTelefone());
            stmt.setString(7, pessoa.getSexo()); // Aqui o valor de sexo já foi validado
            stmt.setString(8, pessoa.getProfissao()); // Aqui o valor de profissao já foi validado
            stmt.setString(9, pessoa.getTipoUsuario()); // Aqui o valor de tipo_usuario já foi validado
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    // Logica do Listar Usuario

     public List<Pessoa> listarUsuarios() {
        List<Pessoa> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setDataNascimento(rs.getString("data_nascimento"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setSexo(rs.getString("sexo"));
                pessoa.setProfissao(rs.getString("profissao"));
                pessoa.setTipoUsuario(rs.getString("tipo_usuario"));
                
                lista.add(pessoa);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Pessoa> buscarPorNome(String nome) {
        List<Pessoa> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios WHERE nome LIKE ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + nome + "%"); // Busca parcial
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setDataNascimento(rs.getString("data_nascimento"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setSexo(rs.getString("sexo"));
                pessoa.setProfissao(rs.getString("profissao"));
                pessoa.setTipoUsuario(rs.getString("tipo_usuario"));
                
                lista.add(pessoa);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    // Listar e editar usuario
    
 public Pessoa buscarPorId(int id) {
    Pessoa pessoa = null;
    String sql = "SELECT * FROM usuarios WHERE id = ?";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            pessoa = new Pessoa();
            pessoa.setId(rs.getInt("id"));
            pessoa.setNome(rs.getString("nome"));
            pessoa.setEmail(rs.getString("email"));
            pessoa.setTelefone(rs.getString("telefone"));
            pessoa.setProfissao(rs.getString("profissao"));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return pessoa;
}

public void atualizarUsuario(Pessoa pessoa) {
    String sql = "UPDATE usuarios SET nome = ?, email = ?, telefone = ?, profissao = ? WHERE id = ?";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, pessoa.getNome());
        stmt.setString(2, pessoa.getEmail());
        stmt.setString(3, pessoa.getTelefone());
        stmt.setString(4, pessoa.getProfissao());
        stmt.setInt(5, pessoa.getId());

        stmt.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}


// excluir 
public void excluirUsuario(int id) {
    String sql = "DELETE FROM usuarios WHERE id = ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        stmt.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    
    
}

