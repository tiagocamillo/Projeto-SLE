package fatec.sjc.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class LanceClienteDTO {
	private double valor;
    private Long idProduto;
    private Long idCliente;
    private LocalDateTime dataHoraLance;
}
