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
@Table(name = "Veiculos")
public class Veiculo {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "Descricao")
    private String descricao;
    
    @Column(name = "Tipo")
    private String tipo;	
    
    @Column(name = "Marca")
    private String marca;

    @Column(name = "Ano")
    private String ano;
    
    @Column(name = "Acessorios")
    private String acessorios;	
    
    @Column(name = "Condicao")
    private String condicao;

    @Column(name = "HistoricoReparo")
    private String historicoReparo;
    
	
}
