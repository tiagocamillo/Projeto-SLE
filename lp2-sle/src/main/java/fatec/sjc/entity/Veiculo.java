package fatec.sjc.entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("VEICULO")
public class Veiculo extends Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private String status;
    private String tipo;

}
