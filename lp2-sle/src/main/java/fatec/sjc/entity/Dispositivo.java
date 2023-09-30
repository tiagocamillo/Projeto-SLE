package fatec.sjc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Dispositivos")
public class Dispositivo {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "Descricao")
    private String descricao;
    
    @Column(name = "Marca")
    private String marca;
    
    @Column(name = "Modelo")
    private String modelo;
    
    @Column(name = "Dimensoes")
    private String dimensoes;
    
    @Column(name = "Condicao")
    private String condicao;
    
    @Column(name = "Especificacoes")
    private String especificacoes;
    
    @Column(name = "HistoricoReparo")
    private String historicoReparo;
	
}
