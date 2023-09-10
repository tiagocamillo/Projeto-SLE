package fatec.sjc.repository;

import fatec.sjc.entity.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class ClienteRepository implements PanacheRepositoryBase<Cliente, Long> {
}

