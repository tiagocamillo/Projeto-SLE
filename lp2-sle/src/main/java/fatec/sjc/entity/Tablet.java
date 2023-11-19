package fatec.sjc.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("TABLET")
public class Tablet extends Dispositivo {

    private String tamanhoTela;
    private String sistemaOperacional;

}
