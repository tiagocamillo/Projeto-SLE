package fatec.sjc.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("LAPTOP")
public class Laptop extends Dispositivo {

    private String tamanhoTela;
    private String processador;

}
