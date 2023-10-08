package fatec.sjc.repository;

import fatec.sjc.entity.Veiculo;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VeiculoRepository implements PanacheRepositoryBase<Veiculo, Long>{
	
}
