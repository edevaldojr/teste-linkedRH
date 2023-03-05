package main.java.teste.backend.linkedrh.models;

import java.time.LocalDate;

public class Funcionario {
    
    private int id;
    private String nome;
    private String cpf;
    private LocalDate nascimento;
    private String cargo;
    private LocalDate adimissao;
    
    public Funcionario(int id, String nome, String cpf, LocalDate nascimento, String cargo, LocalDate adimissao) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.nascimento = nascimento;
        this.cargo = cargo;
        this.adimissao = adimissao;
    }

    public Funcionario(String nome, String cpf, LocalDate nascimento, String cargo, LocalDate adimissao) {
        this.nome = nome;
        this.cpf = cpf;
        this.nascimento = nascimento;
        this.cargo = cargo;
        this.adimissao = adimissao;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public LocalDate getAdimissao() {
        return adimissao;
    }

    public void setAdimissao(LocalDate adimissao) {
        this.adimissao = adimissao;
    }

}
