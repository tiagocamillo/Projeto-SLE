package fatec.sjc.entity;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("VEICULO")
public class Veiculo extends ItemLeilao {

    @Column(name = "Tipo")
    public String tipo;

    @Column(name = "Marca")
    public String marca;

    @Column(name = "Ano")
    public String ano;

    @Column(name = "Acessorios")
    public String acessorios;

}
