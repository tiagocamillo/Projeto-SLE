package fatec.sjc.service;

import fatec.sjc.DTO.TabletDTO;
import fatec.sjc.entity.Tablet;
import fatec.sjc.repository.TabletRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class TabletService {

    @Inject
    TabletRepository tabletRepository;

    @Transactional
    public TabletDTO criarTablet(TabletDTO tabletDTO) {
        Tablet tablet = convertToEntity(tabletDTO);
        tabletRepository.persist(tablet);
        return convertToDTO(tablet);
    }

    @Transactional
    public TabletDTO atualizarTablet(Long id, TabletDTO tabletAtualizadoDTO) {
        Tablet tabletExistente = tabletRepository.findById(id);
        if (tabletExistente != null) {
            updateEntityFromDTO(tabletExistente, tabletAtualizadoDTO);
        }
        return convertToDTO(tabletExistente);
    }

    @Transactional
    public void excluirTablet(Long id) {
        Tablet tabletExistente = tabletRepository.findById(id);
        if (tabletExistente != null) {
            tabletRepository.delete(tabletExistente);
        }
    }

    public TabletDTO buscarTabletPorId(Long id) {
        Tablet tablet = tabletRepository.findById(id);
        return (tablet != null) ? convertToDTO(tablet) : null;
    }

    public List<TabletDTO> listarTodosOsTablets() {
        return tabletRepository.listAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private Tablet convertToEntity(TabletDTO tabletDTO) {
        Tablet tablet = new Tablet();
        tablet.setTamanhoTela(tabletDTO.getTamanhoTela());
        return tablet;
    }

    private void updateEntityFromDTO(Tablet tablet, TabletDTO tabletDTO) {
        tablet.setTamanhoTela(tabletDTO.getTamanhoTela());
    }

    private TabletDTO convertToDTO(Tablet tablet) {
        TabletDTO tabletDTO = new TabletDTO();

        tabletDTO.setTamanhoTela(tablet.getTamanhoTela());
        return tabletDTO;
    }
}