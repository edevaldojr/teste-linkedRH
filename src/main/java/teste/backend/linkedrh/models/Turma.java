package teste.backend.linkedrh.models;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Turma {
    
    private int codigo;
    private LocalDate inicio;
    private LocalDate fim;
    private String local;
    private List<Funcionario> funcionarios;
    private int cursoId;
    
    public Turma(LocalDate inicio, LocalDate fim, String local, int cursoId, List<Funcionario> funcionarios) {
        this.inicio = inicio;
        this.fim = fim;
        this.local = local;
        this.cursoId = cursoId;
        this.funcionarios = funcionarios;
    }

}
