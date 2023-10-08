package fatec.sjc.repository;

import fatec.sjc.entity.Carro;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped

public class CarroRepository implements PanacheRepositoryBase<Carro, Long> {
}
