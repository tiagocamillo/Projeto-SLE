package fatec.sjc.repository;

import fatec.sjc.entity.EntidadeFinanceira;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EntidadeFinanceiraRepository implements PanacheRepository<EntidadeFinanceira> {
}
