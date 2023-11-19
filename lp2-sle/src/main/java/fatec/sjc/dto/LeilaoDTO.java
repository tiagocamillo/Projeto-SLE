package fatec.sjc.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class LeilaoDTO {
    private LocalDateTime dataOcorrencia;
    private LocalDateTime dataFim;
    private String local;
    private String status;
    private List<Long> produtosIds;
    private Long instituicaoFinanceiraId;     
}
