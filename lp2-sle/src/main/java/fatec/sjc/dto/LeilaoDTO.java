package fatec.sjc.dto;

import java.util.List;

import lombok.Data;

@Data
public class LeilaoDTO {
    private String dataOcorrencia;
    private String dataVisita;
    private String local;
    private String status;
    private List<Long> produtosIds;
    private Long instituicaoFinanceiraId;     
}
