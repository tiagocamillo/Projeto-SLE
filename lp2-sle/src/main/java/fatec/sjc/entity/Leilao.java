package fatec.sjc.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "Leilao")
@EqualsAndHashCode(callSuper = false)
public class Leilao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataOcorrencia;
    private LocalDateTime dataFim;
    private String local;
    private String status;


    @JsonInclude(JsonInclude.Include.NON_EMPTY)

    @OneToMany(mappedBy = "leilao", cascade = CascadeType.ALL)
    private List<Produto> produtos;

    @ManyToOne
    @JoinColumn(name = "instituicao_id")
    @JsonbTransient
    public EntidadeFinanceira instituicaoFinanceira;

}
