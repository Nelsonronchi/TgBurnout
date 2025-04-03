package VO;

public class Questionario {
    private int id;
    private String titulo;
    private Autor autor;  // Associação com o Autor

    public Questionario() {
    }

    public Questionario(String titulo, Autor autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
