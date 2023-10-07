package fatec.sjc.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@DiscriminatorValue("tablet")
@Data
public class Tablet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TamanhoTela")
    private double tamanhoTela;
}
