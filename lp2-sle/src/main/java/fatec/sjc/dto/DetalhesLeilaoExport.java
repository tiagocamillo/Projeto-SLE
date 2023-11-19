package fatec.sjc.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class DetalhesLeilaoExport {  private Long leilaoId;
    private LocalDateTime dataOcorrencia;
    private LocalDateTime dataFim;
    private String local;
    private String statusLeilao;
    private String cnpjEntidadeFinanceira;
    private List<ProdutoExportDTO> produtos;

}
