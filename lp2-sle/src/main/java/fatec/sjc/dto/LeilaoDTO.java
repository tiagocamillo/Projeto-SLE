package fatec.sjc.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fatec.sjc.entity.EntidadeFinanceira;
import lombok.Data;

@Data
public class LeilaoDTO {
    private String dataOcorrencia;
    private String dataVisita;
    private String local;
    private String status;
    private List<Long> produtosIds;
    
    @JsonIgnoreProperties("leiloes")
    private EntidadeFinanceira entidadeFinanceira;
}
