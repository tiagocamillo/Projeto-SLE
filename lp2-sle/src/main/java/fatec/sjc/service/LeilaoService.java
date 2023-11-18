package fatec.sjc.service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import fatec.sjc.entity.Leilao;
import fatec.sjc.repository.LeilaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

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
    public void atualizarStatusLeiloes() {
        List<Leilao> leiloes = leilaoRepository.listAll();
        Date currentDate = new Date(System.currentTimeMillis());

        for (Leilao leilao : leiloes) {
            if (leilao.getDataInicio().isAfter(currentDate.toLocalDate().atStartOfDay())) {
                leilao.setStatus("EM ABERTO");
            } else if (leilao.getDataInicio().isBefore(currentDate.toLocalDate().atStartOfDay()) && leilao.getDataFim().isAfter(currentDate.toLocalDate().atStartOfDay())) {
                leilao.setStatus("EM ANDAMENTO");
            } else {
                leilao.setStatus("FINALIZADO");
            }
        }
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
        return leilaoRepository.listAll()
                .stream()
                .sorted((leilao1, leilao2) -> leilao1.getDataInicio().compareTo(leilao2.getDataInicio()))
                .collect(Collectors.toList());
    }
}
