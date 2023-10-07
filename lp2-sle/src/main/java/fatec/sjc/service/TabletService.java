package fatec.sjc.service;

import fatec.sjc.entity.Tablet;
import fatec.sjc.repository.TabletRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class TabletService {

    @Inject
    private TabletRepository tabletRepository;

    @Transactional
    public Tablet criarTablet(Tablet tablet) {
        tabletRepository.persist(tablet);
        return tablet;
    }

    @Transactional
    public Tablet atualizarTablet(Long id, Tablet tablet) {
        Tablet tabletExistente = tabletRepository.findById(id);
        if (tabletExistente != null) {
            // Atualize os atributos do tablet conforme necessário
            tabletRepository.persist(tabletExistente);
            return tabletExistente;
        } else {
            return null;
        }
    }

    @Transactional
    public void deletarTablet(Long id) {
        Tablet tablet = tabletRepository.findById(id);
        if (tablet != null) {
            tabletRepository.delete(tablet);
        }
    }

    public Tablet buscarTabletPorId(Long id) {
        return tabletRepository.findById(id);
    }

    public List<Tablet> listarTablets() {
        return tabletRepository.listAll();
    }
}
