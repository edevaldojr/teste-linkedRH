package teste.backend.linkedrh.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Funcionario {
    
    private int codigo;
    private String nome;
    private String cpf;
    private LocalDate nascimento;
    private String cargo;
    private LocalDate adimissao;
    
}
