package fatec.sjc.dto;

import fatec.sjc.entity.Leilao;
import lombok.Data;

@Data
public class ProdutoDTO {
    private String nome;
    private String descricao;
    private double valorInicial;
    private String status;
    private String tipo;
    private Leilao leilao;
}
