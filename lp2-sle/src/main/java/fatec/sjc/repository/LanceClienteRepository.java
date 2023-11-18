package fatec.sjc.repository;

import fatec.sjc.entity.LanceCliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LanceClienteRepository implements PanacheRepository<LanceCliente> {
}