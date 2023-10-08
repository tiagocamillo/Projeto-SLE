package fatec.sjc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("CARRO")
public class Carro extends Veiculo {

    @Column(name = "NumeroPortas")
    public int numeroPortas;

}
