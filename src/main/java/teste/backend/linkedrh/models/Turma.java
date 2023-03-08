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
    private int curso_id;
    
    public Turma(LocalDate inicio, LocalDate fim, String local, int curso_id, List<Funcionario> funcionarios) {
        this.inicio = inicio;
        this.fim = fim;
        this.local = local;
        this.curso_id = curso_id;
        this.funcionarios = funcionarios;
    }

}
