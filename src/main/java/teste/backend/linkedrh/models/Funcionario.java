package teste.backend.linkedrh.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Funcionario {
    
    private int id;
    private String nome;
    private String cpf;
    private LocalDate nascimento;
    private String cargo;
    private LocalDate adimissao;
    
    public Funcionario(String nome, String cpf, LocalDate nascimento, String cargo, LocalDate adimissao) {
        this.nome = nome;
        this.cpf = cpf;
        this.nascimento = nascimento;
        this.cargo = cargo;
        this.adimissao = adimissao;
    }
}
