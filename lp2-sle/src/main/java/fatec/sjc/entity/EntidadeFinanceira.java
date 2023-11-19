package fatec.sjc.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @JsonManagedReference
    @OneToMany(mappedBy = "instituicaoFinanceira", cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
    private List<Leilao> leiloes;
}
