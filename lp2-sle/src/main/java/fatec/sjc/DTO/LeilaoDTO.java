package fatec.sjc.DTO;

import java.sql.Timestamp;

import lombok.Data;
@Data
public class LeilaoDTO {
    private Long idLeilao;

    private Timestamp dataInicio;
    private Timestamp dataFim;

    private String status;
    private int idEntidadeFinanceiraAssociada;
}
