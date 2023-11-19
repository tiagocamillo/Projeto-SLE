package fatec.sjc.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("CARRO")
public class Carro extends Veiculo {

    private int quantidadeAssentos;
    private String tipoCombustivel;

}

