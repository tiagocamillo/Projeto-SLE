package fatec.sjc.DTO;

import lombok.Data;

@Data
public class ServidorDTO {
    private Long id;
    private String processador;
    private long capacidadeArmazenamento;
}
