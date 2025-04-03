package VO;

public class Autor {
    private int id; // Adiciona o campo ID
    private String nome;
    private String descricao;

    public Autor() {
        // Construtor vazio permitido
    }

    public Autor(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Autor(int id) {
        this.id = id; // Define o ID corretamente
    }

    public int getId() {
        return id; // Retorna o ID armazenado
    }

    public void setId(int id) {
        this.id = id; // Define o ID corretamente
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
