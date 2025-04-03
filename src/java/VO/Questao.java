package VO;

public class Questao {
    private int id;
    private String texto;
    private Questionario questionario;
    private Autor autor;

    public Questao() {
        // Construtor vazio
    }

    public Questao(int id, String texto, Questionario questionario, Autor autor) {
        this.id = id;
        this.texto = texto;
        this.questionario = questionario;
        this.autor = autor;
    }

    public Questao(String texto, Questionario questionario, Autor autor) {
        this.texto = texto;
        this.questionario = questionario;
        this.autor = autor;
    }

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

    public Questionario getQuestionario() {
        return questionario;
    }

    public void setQuestionario(Questionario questionario) {
        this.questionario = questionario;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
