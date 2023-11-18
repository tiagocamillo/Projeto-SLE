package fatec.sjc.service;

import fatec.sjc.dto.LeilaoDTO;
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
    public Leilao salvarLeilao(LeilaoDTO leilaoDTO) {
        Leilao leilao = new Leilao();
        leilao.setDataOcorrencia(leilaoDTO.getDataOcorrencia());
        leilao.setDataVisita(leilaoDTO.getDataVisita());
        leilao.setLocal(leilaoDTO.getLocal());
        leilao.setStatus(leilaoDTO.getStatus());
        leilao.setProdutos(leilaoDTO.getProdutos());
        leilao.setInstituicaoFinanceira(leilaoDTO.getInstituicaoFinanceira());
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
    public void atualizarLeilao(LeilaoDTO leilaoDTO) {
        Leilao leilao = new Leilao();
        leilao.setId(leilaoDTO.getId());
        leilao.setDataOcorrencia(leilaoDTO.getDataOcorrencia());
        leilao.setDataVisita(leilaoDTO.getDataVisita());
        leilao.setLocal(leilaoDTO.getLocal());
        leilao.setStatus(leilaoDTO.getStatus());
        leilao.setProdutos(leilaoDTO.getProdutos());
        leilao.setInstituicaoFinanceira(leilaoDTO.getInstituicaoFinanceira());
        leilaoRepository.persist(leilao);
    }

    @Transactional
    public void excluirLeilao(Long id) {
        leilaoRepository.deleteById(id);
    }
}
