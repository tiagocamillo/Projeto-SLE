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
    public Onibus atualizarOnibus(Long id, Onibus onibusAtualizado) {
        Onibus onibusExistente = onibusRepository.findById(id);
        if (onibusExistente != null) {
            onibusExistente.setCapacidadePassageiros(onibusAtualizado.getCapacidadePassageiros());
        }
        return onibusExistente;
    }

    @Transactional
    public void excluirOnibus(Long id) {
        Onibus onibusExistente = onibusRepository.findById(id);
        if (onibusExistente != null) {
            onibusRepository.delete(onibusExistente);
        }
    }

    public Onibus buscarOnibusPorId(Long id) {
        return onibusRepository.findById(id);
    }

    public List<Onibus> listarTodosOsOnibus() {
        return onibusRepository.listAll();
    }
}
