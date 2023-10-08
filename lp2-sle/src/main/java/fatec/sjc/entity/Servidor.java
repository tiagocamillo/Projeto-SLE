package fatec.sjc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("SERVIDOR")
public class Servidor extends Dispositivo {

    @Column(name = "Processador")
    public String processador;

    @Column(name = "CapacidadeArmazenamento")
    public long capacidadeArmazenamento;

}
