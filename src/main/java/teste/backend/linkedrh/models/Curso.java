package main.java.teste.backend.linkedrh.models;

public class Curso {
    
    private int id;
    private String nome;
    private String descricao;
    private Integer duracao;
    private boolean status;

    public Curso(int id, String nome, String descricao, Integer duracao, boolean status) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
        this.status = status;
    }

    public Curso(String nome, String descricao, Integer duracao, boolean status) {
        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Integer getDuracao() {
        return duracao;
    }
    
    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    
}
