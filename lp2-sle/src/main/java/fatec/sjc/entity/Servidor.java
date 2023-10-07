package fatec.sjc.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("servidor")
public class Servidor extends Dispositivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Processador")
    private String processador;

    @Column(name = "CapacidadeArmazenamento")
    private long capacidadeArmazenamento;

}
