package fatec.sjc.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LaptopDTO extends DispositivoDTO {

    private double tamanhoTela;

}