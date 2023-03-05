package main.java.teste.backend.linkedrh.models;

import java.time.LocalDate;

public class Turma {
    
    private int codigo;
    private LocalDate inicio;
    private LocalDate fim;
    private String local;
    private int curso;

    public Turma(int codigo, LocalDate inicio, LocalDate fim, String local, int curso) {
        this.codigo = codigo;
        this.inicio = inicio;
        this.fim = fim;
        this.local = local;
        this.curso = curso;
    }

    public Turma(LocalDate inicio, LocalDate fim, String local, int curso) {
        this.inicio = inicio;
        this.fim = fim;
        this.local = local;
        this.curso = curso;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFim() {
        return fim;
    }

    public void setFim(LocalDate fim) {
        this.fim = fim;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

}
