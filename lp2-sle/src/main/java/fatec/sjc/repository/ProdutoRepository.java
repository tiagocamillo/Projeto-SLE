package fatec.sjc.repository;
import java.util.List;

import fatec.sjc.entity.Produto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepository<Produto> {
	
	private final EntityManager em;


    public ProdutoRepository(EntityManager em) {
        this.em = em;
    }

    public List<Produto> findByIds(List<Long> produtoIds) {
        return em.createQuery("SELECT p FROM Produto p WHERE p.id IN :ids", Produto.class)
                .setParameter("ids", produtoIds)
                .getResultList();
    }
    
}