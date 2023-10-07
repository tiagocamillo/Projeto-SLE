package fatec.sjc.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("scanner")
public class Scanner extends Dispositivo{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ResolucaoDigitalizacao")
    private int resolucaoDigitalizacao;

}
