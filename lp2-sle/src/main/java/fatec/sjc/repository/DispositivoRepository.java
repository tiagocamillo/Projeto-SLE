package fatec.sjc.repository;

import fatec.sjc.entity.Dispositivo;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DispositivoRepository implements PanacheRepositoryBase<Dispositivo, Long>{

}
