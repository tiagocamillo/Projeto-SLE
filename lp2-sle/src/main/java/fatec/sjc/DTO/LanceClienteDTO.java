package fatec.sjc.DTO;

import lombok.Data;

@Data
public class LanceClienteDTO {
    private Long id;
    private Long idCliente;
    private Long idLeilao;
    private Double valorLance;
}
