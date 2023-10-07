package fatec.sjc.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("laptop")
public class Laptop extends Dispositivo{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "TamanhoTela")
    private double tamanhoTela;

}
