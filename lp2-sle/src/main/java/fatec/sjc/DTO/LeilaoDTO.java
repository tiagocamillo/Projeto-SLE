package fatec.sjc.DTO;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class LeilaoDTO {


    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

    private String status;
    private int idEntidadeFinanceiraAssociada;
}
