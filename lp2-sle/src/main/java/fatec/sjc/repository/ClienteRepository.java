package fatec.sjc.repository;

import fatec.sjc.entity.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {
}