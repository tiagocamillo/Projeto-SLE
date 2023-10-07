package fatec.sjc.service;

import fatec.sjc.entity.Desktop;
import fatec.sjc.repository.DesktopRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class DesktopService {

    @Inject
    private DesktopRepository desktopRepository;

    @Transactional
    public Desktop criarDesktop(Desktop desktop) {
        desktopRepository.persist(desktop);
        return desktop;
    }

    @Transactional
    public Desktop atualizarDesktop(Long id, Desktop desktop) {
        Desktop desktopExistente = desktopRepository.findById(id);
        if (desktopExistente != null) {
            desktopRepository.persist(desktopExistente);
            return desktopExistente;
        } else {
            return null;
        }
    }

    @Transactional
    public void deletarDesktop(Long id) {
        Desktop desktop = desktopRepository.findById(id);
        if (desktop != null) {
            desktopRepository.delete(desktop);
        }
    }

    public Desktop buscarDesktopPorId(Long id) {
        return desktopRepository.findById(id);
    }

    public List<Desktop> listarDesktops() {
        return desktopRepository.listAll();
    }
}
