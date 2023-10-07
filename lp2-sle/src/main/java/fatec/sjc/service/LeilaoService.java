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
        leilao.persist();
        return leilao;
    }

    @Transactional
    public Leilao atualizarLeilao(Long id, Leilao leilao) {
        leilaoRepository.persist(leilao);
        return leilao;
    }

    @Transactional
    public void deletarLeilao(Long id) {
        Leilao leilao = leilaoRepository.findById(id);
        if (leilao != null) {
            leilaoRepository.delete(leilao);
        }
    }

    public Leilao buscarLeilaoPorId(Long id) {
        return leilaoRepository.findById(id);
    }

    public List<Leilao> listarTodosLeiloes() {
        return leilaoRepository.listAll();
    }
}