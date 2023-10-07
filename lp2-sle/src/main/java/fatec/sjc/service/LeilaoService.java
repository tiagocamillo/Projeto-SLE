package fatec.sjc.service;

import fatec.sjc.entity.Leilao;
import fatec.sjc.repository.LeilaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class LeilaoService {

    @Inject
    LeilaoRepository leilaoRepository;

    @Transactional
    public Leilao criarLeilao(Leilao leilao) {
        leilaoRepository.persist(leilao);
        return leilao;
    }

    @Transactional
    public Leilao atualizarLeilao(Long id, Leilao leilaoAtualizado) {
        Leilao leilaoExistente = leilaoRepository.findById(id);
        if (leilaoExistente != null) {
            // Atualize os campos necessários
        }
        return leilaoExistente;
    }

    @Transactional
    public void excluirLeilao(Long id) {
        Leilao leilaoExistente = leilaoRepository.findById(id);
        if (leilaoExistente != null) {
            leilaoRepository.delete(leilaoExistente);
        }
    }

    public Leilao buscarLeilaoPorId(Long id) {
        return leilaoRepository.findById(id);
    }

    public List<Leilao> listarTodosOsLeiloes() {
        return leilaoRepository.listAll();
    }
}
