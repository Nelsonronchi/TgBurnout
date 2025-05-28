package VO;

public class Pergunta {
    private int id;
    private String texto;
    private int questionarioId; // opcional, se quiser guardar o id do questionário associado

    public Pergunta() {
    }

    public Pergunta(int id, String texto, int questionarioId) {
        this.id = id;
        this.texto = texto;
        this.questionarioId = questionarioId;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getQuestionarioId() {
        return questionarioId;
    }

    public void setQuestionarioId(int questionarioId) {
        this.questionarioId = questionarioId;
    }
}
