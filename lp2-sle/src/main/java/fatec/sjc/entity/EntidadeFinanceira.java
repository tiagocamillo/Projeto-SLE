package fatec.sjc.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "EntidadesFinanceiras")
public class EntidadeFinanceira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEntidadeFinanceira;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cnpj", unique = true)
    private String cnpj;
}
