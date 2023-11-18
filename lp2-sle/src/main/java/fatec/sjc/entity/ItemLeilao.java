package fatec.sjc.entity;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ItemLeilao")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_item")
public class ItemLeilao extends PanacheEntity {


    @Column(name = "Nome")
    public String nome;

    @Column(name = "Descricao")
    public String descricao;

    @Column(name = "Condicao")
    public String condicao;

    @Column(name = "HistoricoReparo")
    public String historicoReparo;

    @ManyToOne
    @JoinColumn(name = "IDLeilao")
    private Leilao leilao;


}
