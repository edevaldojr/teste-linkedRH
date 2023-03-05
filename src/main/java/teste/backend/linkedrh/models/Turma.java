package teste.backend.linkedrh.models;

import java.time.LocalDate;

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
    private int curso;
    
    public Turma(LocalDate inicio, LocalDate fim, String local, int curso) {
        this.inicio = inicio;
        this.fim = fim;
        this.local = local;
        this.curso = curso;
    }

}
