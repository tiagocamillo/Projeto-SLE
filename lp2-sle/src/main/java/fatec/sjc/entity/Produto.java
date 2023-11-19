package fatec.sjc.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import jakarta.persistence.*;

import java.util.List;

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
    @ManyToOne(  cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "leilao_id")
    private Leilao leilao;

    @JsonIgnore
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<LanceCliente> lances;
}

