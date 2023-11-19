package fatec.sjc.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Produto")
public class Produto {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private String status;
    private String tipo;
    private double lanceInicial;
    private double lanceAdicional;

    @JsonBackReference
    @ManyToOne
    private Leilao leilao;

    @JsonManagedReference
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<LanceCliente> lances;

}
