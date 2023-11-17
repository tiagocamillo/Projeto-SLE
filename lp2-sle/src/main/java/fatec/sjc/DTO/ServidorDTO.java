package fatec.sjc.DTO;

import lombok.Data;


@Data
public class ServidorDTO extends DispositivoDTO {

    private String processador;
    private long capacidadeArmazenamento;

}