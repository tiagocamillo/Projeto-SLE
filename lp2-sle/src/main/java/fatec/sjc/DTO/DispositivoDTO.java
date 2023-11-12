package fatec.sjc.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DispositivoDTO extends ItemLeilaoDTO {

    private String marca;

    private String modelo;

    private String dimensoes;

    private String especificacoes;

}
