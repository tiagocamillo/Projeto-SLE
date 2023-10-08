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
    TabletRepository tabletRepository;

    @Transactional
    public Tablet criarTablet(Tablet tablet) {
        tabletRepository.persist(tablet);
        return tablet;
    }

    @Transactional
    public Tablet atualizarTablet(Long id, Tablet tabletAtualizado) {
        Tablet tabletExistente = tabletRepository.findById(id);
        if (tabletExistente != null) {
            tabletExistente.setTamanhoTela(tabletAtualizado.getTamanhoTela());
        }
        return tabletExistente;
    }

    @Transactional
    public void excluirTablet(Long id) {
        Tablet tabletExistente = tabletRepository.findById(id);
        if (tabletExistente != null) {
            tabletRepository.delete(tabletExistente);
        }
    }

    public Tablet buscarTabletPorId(Long id) {
        return tabletRepository.findById(id);
    }

    public List<Tablet> listarTodosOsTablets() {
        return tabletRepository.listAll();
    }
}
