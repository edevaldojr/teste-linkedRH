package teste.backend.linkedrh.models.dtos;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class TurmaDTO {

    @NotNull(message = "Campo inicio obrigatório")
    private LocalDate inicio;
    @NotNull(message = "Campo fim obrigatório")
    private LocalDate fim;
    @NotNull(message = "Campo local obrigatório")
    private String local;
    @NotNull(message = "Campo cursoId obrigatório")
    private int cursoId;

}
