package teste.backend.linkedrh.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Curso {
    
    private int codigo;
    private String nome;
    private String descricao;
    private Integer duracao;
    
}
