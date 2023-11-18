package fatec.sjc.service;

import fatec.sjc.dto.TabletDTO;
import fatec.sjc.entity.Tablet;
import fatec.sjc.repository.TabletRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class TabletService {

    private final TabletRepository tabletRepository;

    @Inject
    public TabletService(TabletRepository tabletRepository) {
        this.tabletRepository = tabletRepository;
    }

    @Transactional
    public Tablet salvarTablet(TabletDTO tabletDTO) {
        Tablet tablet = new Tablet();
        tablet.setTamanhoTela(tabletDTO.getTamanhoTela());
        tablet.setSistemaOperacional(tabletDTO.getSistemaOperacional());
        tabletRepository.persist(tablet);
        return tablet;
    }

    public List<Tablet> listarTablets() {
        return tabletRepository.listAll();
    }

    public Tablet buscarPorId(Long id) {
        return tabletRepository.findById(id);
    }

    @Transactional
    public void atualizarTablet(TabletDTO tabletDTO) {
        Tablet tablet = new Tablet();
        tablet.setTamanhoTela(tabletDTO.getTamanhoTela());
        tablet.setSistemaOperacional(tabletDTO.getSistemaOperacional());
        tabletRepository.persist(tablet);
    }

    @Transactional
    public void excluirTablet(Long id) {
        tabletRepository.deleteById(id);
    }
}
