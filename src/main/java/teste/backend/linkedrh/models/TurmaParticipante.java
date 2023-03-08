package teste.backend.linkedrh.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurmaParticipante {
    
    private int codigo;
    private int funcionarioId;
    private int turmaId;

}
