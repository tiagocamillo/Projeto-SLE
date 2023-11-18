package fatec.sjc.dto;

import jakarta.ws.rs.Path;
import lombok.Data;

@Data
public class EntidadeFinanceiraDTO {
    private String cnpj;
    private String nome;
    private String detalhesContato;
}
