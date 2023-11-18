package fatec.sjc.dto;

import fatec.sjc.entity.Cliente;
import fatec.sjc.entity.Produto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LanceClienteDTO {
    private double valor;
    private Produto produto;
    private Cliente cliente;
    private LocalDateTime dataHoraLance;
}
