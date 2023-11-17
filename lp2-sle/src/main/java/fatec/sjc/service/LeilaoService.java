package fatec.sjc.service;

import fatec.sjc.entity.Leilao;
import fatec.sjc.repository.LeilaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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
    public Leilao atualizarLeilao(Long id, Leilao leilaoDTO) {
        Leilao leilaoExistente = leilaoRepository.findById(id);
        if (leilaoExistente != null) {
            leilaoExistente.setDataInicio(leilaoDTO.getDataInicio());
            leilaoExistente.setDataFim(leilaoDTO.getDataFim());
            leilaoExistente.setStatus(leilaoDTO.getStatus());
            leilaoExistente.setIdEntidadeFinanceiraAssociada(leilaoDTO.getIdEntidadeFinanceiraAssociada());
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
    
    public List<Leilao> listarLeiloesOrdenadosPorData() {
        // Consulta os leilões e ordena por data de início
        List<Leilao> leiloesOrdenados = leilaoRepository.listAll()
                .stream()
                .sorted((leilao1, leilao2) -> leilao1.getDataInicio().compareTo(leilao2.getDataInicio()))
                .collect(Collectors.toList());
        return leiloesOrdenados;
    }
    
}