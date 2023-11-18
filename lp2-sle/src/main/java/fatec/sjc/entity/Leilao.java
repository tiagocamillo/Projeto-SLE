package fatec.sjc.entity;

import java.time.LocalDateTime;
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

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "Leilao")
@EqualsAndHashCode(callSuper = false)
public class Leilao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDLeilao")
    private Long idLeilao;

    @Column(name = "DataInicio")
    private LocalDateTime dataInicio;

    @Column(name = "DataFim")
    private LocalDateTime dataFim;

    @Column(name = "Status", length = 20)
    private String status;

    @Column(name = "IDEntidadeFinanceiraAssociada")
    private int idEntidadeFinanceiraAssociada;

    @ManyToOne
    @JoinColumn(name = "IDEntidadeFinanceiraAssociada", referencedColumnName = "idEntidadeFinanceira", insertable = false, updatable = false)
    private EntidadeFinanceira entidadeFinanceiraAssociada;

    @OneToMany(mappedBy = "leilao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LanceCliente> lances = new ArrayList<>();

    @OneToMany(mappedBy = "leilao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemLeilao> itensLeilao = new ArrayList<>();



}
