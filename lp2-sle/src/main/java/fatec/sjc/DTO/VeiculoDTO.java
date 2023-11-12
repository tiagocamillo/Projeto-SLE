package fatec.sjc.DTO;

import lombok.Data;

@Data
public class VeiculoDTO {
    private Long id;
    private String tipo;
    private String marca;
    private String ano;
    private String acessorios;
}
