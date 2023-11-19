package fatec.sjc.dto;

import lombok.Data;

@Data
public class ProdutoDTO {
    private String nome;
    private String descricao;
    private String status;
    private String tipo;
    private double lanceInicial;
    private double lanceAdicional;
}

