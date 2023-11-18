package fatec.sjc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("ONIBUS")
public class Onibus extends Veiculo {

    private int quantidadeAssentos;
    private int quantidadePortas;
}
