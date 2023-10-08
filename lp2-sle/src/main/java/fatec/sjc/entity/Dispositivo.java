package fatec.sjc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("DISPOSITIVO")
public class Dispositivo extends ItemLeilao {
    
    @Column(name = "Marca")
    private String marca;
    
    @Column(name = "Modelo")
    private String modelo;
    
    @Column(name = "Dimensoes")
    private String dimensoes;
    
    @Column(name = "Especificacoes")
    private String especificacoes;
    
}
