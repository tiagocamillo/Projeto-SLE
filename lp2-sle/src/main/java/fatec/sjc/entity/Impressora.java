package fatec.sjc.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@DiscriminatorValue("impressora")
public class Impressora extends Dispositivo{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TipoImpressao")
    private String tipoImpressao;

}