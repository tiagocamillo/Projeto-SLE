package fatec.sjc.service;

import fatec.sjc.entity.Onibus;
import fatec.sjc.repository.OnibusRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class OnibusService {

    private final OnibusRepository onibusRepository;

    @Inject
    public OnibusService(OnibusRepository onibusRepository) {
        this.onibusRepository = onibusRepository;
    }

    @Transactional
    public Onibus salvarOnibus(Onibus onibus) {
        onibusRepository.persist(onibus);
        return onibus;
    }

    public List<Onibus> listarOnibus() {
        return onibusRepository.listAll();
    }

    public Onibus buscarPorId(Long id) {
        return onibusRepository.findById(id);
    }

    @Transactional
    public void atualizarOnibus(Onibus onibus) {
        onibusRepository.persist(onibus);
    }

    @Transactional
    public void excluirOnibus(Long id) {
        onibusRepository.deleteById(id);
    }
}