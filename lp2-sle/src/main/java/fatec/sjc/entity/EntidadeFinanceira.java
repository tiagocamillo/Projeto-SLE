package fatec.sjc.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "EntidadesFinanceiras")
public class EntidadeFinanceira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cnpj;
    private String nome;
    private String detalhesContato;

    @OneToMany(mappedBy = "instituicaoFinanceira", cascade = CascadeType.ALL)
    private List<Leilao> leiloes;
}
