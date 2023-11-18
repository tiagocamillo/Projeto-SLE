package fatec.sjc.DTO;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
@Data
public class LeilaoDTO {

    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private String status;
    private int idEntidadeFinanceiraAssociada;
    private List<LanceClienteDTO> lances;
    private List<ItemLeilaoDTO> itensLeilao;



}
