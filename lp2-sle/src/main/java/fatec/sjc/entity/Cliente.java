package fatec.sjc.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@Entity
@Table(name = "Cliente")
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING)

public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String detalhesContato;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<LanceCliente> lances;

}
