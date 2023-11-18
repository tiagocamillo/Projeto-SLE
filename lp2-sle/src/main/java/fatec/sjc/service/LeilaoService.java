package fatec.sjc.service;

import fatec.sjc.entity.Leilao;
import fatec.sjc.repository.LeilaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
@ApplicationScoped
public class LeilaoService {

    private final LeilaoRepository leilaoRepository;

    @Inject
    public LeilaoService(LeilaoRepository leilaoRepository) {
        this.leilaoRepository = leilaoRepository;
    }

    @Transactional
    public Leilao salvarLeilao(Leilao leilao) {
        leilaoRepository.persist(leilao);
        return leilao;
    }

    public List<Leilao> listarLeiloes() {
        return leilaoRepository.listAll();
    }

    public Leilao buscarPorId(Long id) {
        return leilaoRepository.findById(id);
    }

    @Transactional
    public void atualizarLeilao(Leilao leilao) {
        leilaoRepository.persist(leilao);
    }

    @Transactional
    public void excluirLeilao(Long id) {
        leilaoRepository.deleteById(id);
    }
}