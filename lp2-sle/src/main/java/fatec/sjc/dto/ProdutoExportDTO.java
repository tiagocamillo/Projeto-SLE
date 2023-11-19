package fatec.sjc.dto;

import java.util.List;

import lombok.Data;

@Data
public class ProdutoExportDTO {

    private Long produtoId;
    private String nomeProduto;
    private String descricao;
    private String statusProduto;
    private String tipoProduto;
    private double lanceInicial;
    private double lanceAdicional;
    private List<LanceClienteDTO> lances;
}
