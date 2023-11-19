package fatec.sjc.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

}
