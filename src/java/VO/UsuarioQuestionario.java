package VO;

public class UsuarioQuestionario {
    private int id;
    private Usuario usuario;
    private Questionario questionario;

    public UsuarioQuestionario() {
    }

    public UsuarioQuestionario(int id, Usuario usuario, Questionario questionario) {
        this.id = id;
        this.usuario = usuario;
        this.questionario = questionario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Questionario getQuestionario() {
        return questionario;
    }

    public void setQuestionario(Questionario questionario) {
        this.questionario = questionario;
    }
    private String titulo;

public void setTitulo(String titulo) {
    this.titulo = titulo;
}

public String getTitulo() {
    return titulo;
}
}
