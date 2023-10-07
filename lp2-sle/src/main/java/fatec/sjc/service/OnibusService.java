package fatec.sjc.service;

import fatec.sjc.entity.Onibus;
import fatec.sjc.repository.OnibusRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class OnibusService {

    @Inject
    OnibusRepository onibusRepository;

    @Transactional
    public Onibus criarOnibus(Onibus onibus) {
        onibusRepository.persist(onibus);
        return onibus;
    }

    @Transactional
    public Onibus atualizarOnibus(Long id, Onibus onibus) {
        onibusRepository.persist(onibus);
        return onibus;
    }

    @Transactional
    public void deletarOnibus(Long id) {
        Onibus onibus = onibusRepository.findById(id);
        if (onibus != null) {
            onibusRepository.delete(onibus);
        }
    }

    public Onibus buscarOnibusPorId(Long id) {
        return onibusRepository.findById(id);
    }

    public List<Onibus> listarOnibus() {
        return onibusRepository.listAll();
    }

}