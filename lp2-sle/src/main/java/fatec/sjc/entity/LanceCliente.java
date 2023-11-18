package fatec.sjc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@Entity
@Table(name = "LanceCliente")
public class LanceCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double valor;

    @JsonBackReference
    @ManyToOne
    private Produto produto;

    @JsonBackReference
    @ManyToOne
    private Cliente cliente;

    private LocalDateTime dataHoraLance;

}
