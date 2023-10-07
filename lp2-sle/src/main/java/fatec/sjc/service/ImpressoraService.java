package fatec.sjc.service;

import fatec.sjc.entity.Impressora;
import fatec.sjc.repository.ImpressoraRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ImpressoraService {

    @Inject
    private ImpressoraRepository impressoraRepository;

    @Transactional
    public Impressora criarImpressora(Impressora impressora) {
        impressoraRepository.persist(impressora);
        return impressora;
    }

    @Transactional
    public Impressora atualizarImpressora(Long id, Impressora impressora) {
        Impressora impressoraExistente = impressoraRepository.findById(id);
        if (impressoraExistente != null) {
            impressoraRepository.persist(impressoraExistente);
            return impressoraExistente;
        } else {
            return null;
        }
    }

    @Transactional
    public void deletarImpressora(Long id) {
        Impressora impressora = impressoraRepository.findById(id);
        if (impressora != null) {
            impressoraRepository.delete(impressora);
        }
    }

    public Impressora buscarImpressoraPorId(Long id) {
        return impressoraRepository.findById(id);
    }

    public List<Impressora> listarImpressoras() {
        return impressoraRepository.listAll();
    }
}
