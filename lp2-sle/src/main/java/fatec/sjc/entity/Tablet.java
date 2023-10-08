package fatec.sjc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("TABLET")
public class Tablet extends Dispositivo {

    @Column(name = "TamanhoTela")
    public double tamanhoTela;

}
