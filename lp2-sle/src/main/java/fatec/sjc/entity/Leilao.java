package fatec.sjc.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "Leilao")
@EqualsAndHashCode(callSuper = false)
public class Leilao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dataOcorrencia;
    private String dataVisita;
    private String local;
    private String status;

    @JsonManagedReference
    @OneToMany(mappedBy = "leilao", cascade = CascadeType.ALL)
    private List<Produto> produtos;
    
    @JsonBackReference
    @ManyToOne
    private EntidadeFinanceira instituicaoFinanceira;



}
