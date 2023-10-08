package fatec.sjc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("LAPTOP")
public class Laptop extends Dispositivo {

    @Column(name = "TamanhoTela")
    public double tamanhoTela;

}
