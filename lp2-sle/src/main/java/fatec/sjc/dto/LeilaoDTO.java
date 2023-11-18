package fatec.sjc.dto;

import fatec.sjc.entity.EntidadeFinanceira;
import fatec.sjc.entity.Produto;
import lombok.Data;

import java.util.List;

@Data
public class LeilaoDTO {
    private Long id;

    private String dataOcorrencia;
    private String dataVisita;
    private String local;
    private String status;
    private List<Produto> produtos;
    private EntidadeFinanceira instituicaoFinanceira;
}
