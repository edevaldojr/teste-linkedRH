package teste.backend.linkedrh.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TurmaParticipante {
    
    private int codigo;
    private int turma;
    private int funcionario;

    public TurmaParticipante(int turma, int funcionario) {
        this.turma = turma;
        this.funcionario = funcionario;
    }

}
