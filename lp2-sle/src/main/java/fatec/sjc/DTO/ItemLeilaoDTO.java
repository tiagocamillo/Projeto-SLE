package fatec.sjc.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ItemLeilaoDTO {
    private String nome;

    private String descricao;

    private String condicao;

    private String historicoReparo;
}
