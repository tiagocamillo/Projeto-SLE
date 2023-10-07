package fatec.sjc.repository;

import fatec.sjc.entity.Van;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VanRepository implements PanacheRepositoryBase<Van, Long> {
}