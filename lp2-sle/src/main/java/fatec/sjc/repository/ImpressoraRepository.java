package fatec.sjc.repository;

import fatec.sjc.entity.Impressora;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ImpressoraRepository implements PanacheRepositoryBase<Impressora, Long> {
}
