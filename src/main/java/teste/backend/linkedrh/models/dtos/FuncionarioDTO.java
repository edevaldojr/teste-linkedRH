package teste.backend.linkedrh.models.dtos;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioDTO {
    
    @NotNull(message = "Campo nome obrigatório")
    private String nome;
    @NotNull(message = "Campo CPF obrigatório")
    private String cpf;
    @NotNull(message = "Campo data de nascimento obrigatório")
    private LocalDate nascimento;
    @NotNull(message = "Campo cargo obrigatório")
    private String cargo;
    @NotNull(message = "Campo data de admissão obrigatório")
    private LocalDate adimissao;

}
