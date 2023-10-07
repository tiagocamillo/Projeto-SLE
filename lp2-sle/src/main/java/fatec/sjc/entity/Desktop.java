package fatec.sjc.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("desktop")
public class Desktop extends Dispositivo{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Processador")
    private String processador;

}
