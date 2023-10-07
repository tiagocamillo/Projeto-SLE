package fatec.sjc.repository;

import fatec.sjc.entity.Tablet;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TabletRepository implements PanacheRepositoryBase<Tablet, Long> {
}
