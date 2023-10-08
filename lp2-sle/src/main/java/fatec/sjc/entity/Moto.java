package fatec.sjc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("MOTO")
public class Moto extends Veiculo {

    @Column(name = "Cilindrada")
    public int cilindrada;

}