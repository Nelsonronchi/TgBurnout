package VO;

public class Resposta {
    private int id;
    private int usuarioId;
    private int questionarioId;
    private int questaoId;
    private int valorResposta;
    private String cpfUsuario;

    // getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }

    public int getQuestionarioId() { return questionarioId; }
    public void setQuestionarioId(int questionarioId) { this.questionarioId = questionarioId; }

    public int getQuestaoId() { return questaoId; }
    public void setQuestaoId(int questaoId) { this.questaoId = questaoId; }

    public int getValorResposta() { return valorResposta; }
    public void setValorResposta(int valorResposta) { this.valorResposta = valorResposta; }

    public String getCpfUsuario() { return cpfUsuario; }
    public void setCpfUsuario(String cpfUsuario) { this.cpfUsuario = cpfUsuario; }
}
