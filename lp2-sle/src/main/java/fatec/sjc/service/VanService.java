package fatec.sjc.service;

import fatec.sjc.entity.Van;
import fatec.sjc.repository.VanRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class VanService {

    @Inject
    VanRepository vanRepository;

    @Transactional
    public Van criarVan(Van van) {
        vanRepository.persist(van);
        return van;
    }

    @Transactional
    public Van atualizarVan(Long id, Van van) {
        vanRepository.persist(van);
        return van;
    }

    @Transactional
    public void deletarVan(Long id) {
        Van van = vanRepository.findById(id);
        if (van != null) {
            vanRepository.delete(van);
        }
    }

    public Van buscarVanPorId(Long id) {
        return vanRepository.findById(id);
    }

    public List<Van> listarVans() {
        return vanRepository.listAll();
    }

}