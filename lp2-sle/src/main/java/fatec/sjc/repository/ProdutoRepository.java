package fatec.sjc.repository;
import fatec.sjc.entity.Produto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped

public class ProdutoRepository implements PanacheRepository<Produto> {
}