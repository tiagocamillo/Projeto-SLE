package fatec.sjc.dto;

import java.time.LocalDateTime;
import java.util.List;

import fatec.sjc.entity.Cliente;
import fatec.sjc.entity.Produto;
import lombok.Data;

@Data
public class DetalhesLeilaoDTO {
    private LocalDateTime dataOcorrencia;
    private LocalDateTime dataFim;
    private String local;
    private String status;
    private int quantidadeTotalProdutos;
    private List<Produto> produtos;
    private EntidadeFinanceiraDTO entidadeFinanceira;

    private List<Cliente> clientesGanhadores; 
    private double valorLanceVencedor;
}

