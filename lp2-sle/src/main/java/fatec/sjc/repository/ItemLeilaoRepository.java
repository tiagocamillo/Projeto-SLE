package fatec.sjc.repository;

import fatec.sjc.entity.ItemLeilao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ItemLeilaoRepository implements PanacheRepository<ItemLeilao> {
}
