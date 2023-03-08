package teste.backend.linkedrh.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoDTO {

    @NotNull(message = "Campo nome obrigatório")
    private String nome;
    @NotNull(message = "Campo descrição obrigatório")
    private String descricao;
    @NotNull(message = "Campo duração obrigatório")
    private Integer duracao;

}
