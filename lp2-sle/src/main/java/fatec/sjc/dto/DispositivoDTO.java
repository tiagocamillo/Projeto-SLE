package fatec.sjc.dto;

import lombok.Data;

@Data
public class DispositivoDTO {
    private Long id;
    private String nome;
    private String descricao;
    private double valorInicial;
    private String status;
    private String tipo;

}
