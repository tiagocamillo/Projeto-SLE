package fatec.sjc.DTO;

import lombok.Data;

@Data
public class LanceClienteDTO {
    private Long idCliente;
    private Long idLeilao;
    private Double valorLance;
}
