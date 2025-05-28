package VO;

public class GrupoQuestionario {
    private int id;
    private Grupo grupo;
    private Questionario questionario;

    public GrupoQuestionario() {
    }

    public GrupoQuestionario(int id, Grupo grupo, Questionario questionario) {
        this.id = id;
        this.grupo = grupo;
        this.questionario = questionario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Questionario getQuestionario() {
        return questionario;
    }

    public void setQuestionario(Questionario questionario) {
        this.questionario = questionario;
    }
}
