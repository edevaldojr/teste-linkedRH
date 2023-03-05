package teste.backend.linkedrh.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Curso {
    
    private int id;
    private String nome;
    private String descricao;
    private Integer duracao;
    private boolean status;

    public Curso(String nome, String descricao, Integer duracao, boolean status) {
        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
        this.status = status;
    }
    
}
