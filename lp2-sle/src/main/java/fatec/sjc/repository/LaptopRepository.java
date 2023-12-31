package fatec.sjc.repository;

import fatec.sjc.entity.Laptop;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LaptopRepository implements PanacheRepository<Laptop> {
}