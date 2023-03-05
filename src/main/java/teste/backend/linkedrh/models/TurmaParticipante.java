package main.java.teste.backend.linkedrh.models;

public class TurmaParticipante {
    
    private int codigo;
    private int turma;
    private int funcionario;

    public TurmaParticipante(int codigo, int turma, int funcionario) {
        this.codigo = codigo;
        this.turma = turma;
        this.funcionario = funcionario;
    }

    public TurmaParticipante(int turma, int funcionario) {
        this.turma = turma;
        this.funcionario = funcionario;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getTurma() {
        return turma;
    }

    public void setTurma(int turma) {
        this.turma = turma;
    }

    public int getFuncionario() {
        return funcionario;
    }
    
    public void setFuncionario(int funcionario) {
        this.funcionario = funcionario;
    }

}
