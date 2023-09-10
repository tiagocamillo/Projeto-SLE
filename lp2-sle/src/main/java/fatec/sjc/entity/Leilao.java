package fatec.sjc.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Entity
@Data
@Table(name = "Leilao")
@EqualsAndHashCode(callSuper = false)

public class Leilao extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDLeilao")
    private Long idLeilao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DataInicio")
    private Date dataInicio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DataFim")
    private Date dataFim;

    @Column(name = "Status", length = 20)
    private String status;

    @Column(name = "IDEntidadeFinanceiraAssociada")
    private int idEntidadeFinanceiraAssociada;

    @ManyToOne
    @JoinColumn(name = "IDEntidadeFinanceiraAssociada", referencedColumnName = "IDEntidadeFinanceira", insertable = false, updatable = false)
    private EntidadeFinanceira entidadeFinanceiraAssociada;

}
