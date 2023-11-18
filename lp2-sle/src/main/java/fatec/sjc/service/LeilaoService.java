package fatec.sjc.service;

import java.util.List;

import fatec.sjc.dto.LeilaoDTO;
import fatec.sjc.entity.EntidadeFinanceira;
import fatec.sjc.entity.Leilao;
import fatec.sjc.entity.Produto;
import fatec.sjc.repository.EntidadeFinanceiraRepository;
import fatec.sjc.repository.LeilaoRepository;
import fatec.sjc.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class LeilaoService {

	private final LeilaoRepository leilaoRepository;
    private final ProdutoRepository produtoRepository;
    private final EntidadeFinanceiraRepository entidadeFinanceiraRepository;

    public LeilaoService(LeilaoRepository leilaoRepository, ProdutoRepository produtoRepository,
                         EntidadeFinanceiraRepository entidadeFinanceiraRepository) {
        this.leilaoRepository = leilaoRepository;
        this.produtoRepository = produtoRepository;
        this.entidadeFinanceiraRepository = entidadeFinanceiraRepository;
    }

    @Transactional
    public Leilao salvarLeilao(LeilaoDTO leilaoDTO) {
        Leilao leilao = new Leilao();
        leilao.setDataOcorrencia(leilaoDTO.getDataOcorrencia());
        leilao.setDataVisita(leilaoDTO.getDataVisita());
        leilao.setLocal(leilaoDTO.getLocal());
        leilao.setStatus(leilaoDTO.getStatus());

        List<Produto> produtos = produtoRepository.findByIds(leilaoDTO.getProdutosIds());
        leilao.setProdutos(produtos);

        EntidadeFinanceira entidadeFinanceira = entidadeFinanceiraRepository.findById(leilaoDTO.getEntidadeFinanceira().getId());
        leilao.setInstituicaoFinanceira(entidadeFinanceira);

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
        leilao.setDataOcorrencia(leilaoDTO.getDataOcorrencia());
        leilao.setDataVisita(leilaoDTO.getDataVisita());
        leilao.setLocal(leilaoDTO.getLocal());
        leilao.setStatus(leilaoDTO.getStatus());
        
        List<Produto> produtos = produtoRepository.findByIds(leilaoDTO.getProdutosIds());
        leilao.setProdutos(produtos);

        EntidadeFinanceira entidadeFinanceira = entidadeFinanceiraRepository.findById(leilaoDTO.getEntidadeFinanceira().getId());
        leilao.setInstituicaoFinanceira(entidadeFinanceira);

        leilaoRepository.persist(leilao);
    }

    @Transactional
    public void excluirLeilao(Long id) {
        leilaoRepository.deleteById(id);
    }
}
