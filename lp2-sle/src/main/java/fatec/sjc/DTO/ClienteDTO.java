package fatec.sjc.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClienteDTO {
    private String nome;

    private String cpfcnpj;

}
