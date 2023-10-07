package fatec.sjc.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "carro")
public class Carro extends Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numeroPortas;


}
