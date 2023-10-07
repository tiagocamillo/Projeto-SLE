package fatec.sjc.repository;

import fatec.sjc.entity.Desktop;
import fatec.sjc.entity.Laptop;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped

public class DesktopRepository implements PanacheRepositoryBase<Desktop, Long> {
}
