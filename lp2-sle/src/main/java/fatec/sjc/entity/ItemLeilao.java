package fatec.sjc.entity;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;

@Data
@Entity
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

}