package fatec.sjc.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;
@Data
public class LeilaoDTO {
    private Long idLeilao;

    private Date dataInicio;

    private Date dataFim;

    private String status;
    private int idEntidadeFinanceiraAssociada;
}
