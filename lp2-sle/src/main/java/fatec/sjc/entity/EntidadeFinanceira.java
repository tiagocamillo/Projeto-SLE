package fatec.sjc.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

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

    @JsonBackReference
    @OneToMany(mappedBy = "instituicaoFinanceira", cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
    private List<Leilao> leiloes;
}
