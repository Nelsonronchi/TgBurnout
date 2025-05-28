package DAO;

import VO.Resposta;
import Conexao.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RespostaDAO {

    public void salvar(Resposta resposta) throws Exception {
        String sql = "INSERT INTO respostas (usuario_id, questionario_id, questao_id, valor_resposta, cpf_usuario) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, resposta.getUsuarioId());
            ps.setInt(2, resposta.getQuestionarioId());
            ps.setInt(3, resposta.getQuestaoId());
            ps.setInt(4, resposta.getValorResposta());
            ps.setString(5, resposta.getCpfUsuario());

            ps.executeUpdate();
        }
    }
}
