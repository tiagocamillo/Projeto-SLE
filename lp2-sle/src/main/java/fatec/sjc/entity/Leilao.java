package fatec.sjc.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "Leilao")
@EqualsAndHashCode(callSuper = false)
public class Leilao  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDLeilao")
    private Long idLeilao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DataInicio")
    private Timestamp dataInicio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DataFim")
    private Timestamp dataFim;

    @Column(name = "Status", length = 20)
    private String status;

    @Column(name = "IDEntidadeFinanceiraAssociada")
    private int idEntidadeFinanceiraAssociada;

    @ManyToOne
    @JoinColumn(name = "IDEntidadeFinanceiraAssociada", referencedColumnName = "IDEntidadeFinanceira", insertable = false, updatable = false)
    private EntidadeFinanceira entidadeFinanceiraAssociada;

    @OneToMany(mappedBy = "leilao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LanceCliente> lances = new ArrayList<>();

    public void setId(Long idLeilao) {
    }
}
