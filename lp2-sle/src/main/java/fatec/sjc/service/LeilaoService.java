package fatec.sjc.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import fatec.sjc.dto.DetalhesLeilaoDTO;
import fatec.sjc.dto.EntidadeFinanceiraDTO;
import fatec.sjc.dto.LeilaoDTO;
import fatec.sjc.entity.EntidadeFinanceira;
import fatec.sjc.entity.Leilao;
import fatec.sjc.entity.Produto;
import fatec.sjc.repository.EntidadeFinanceiraRepository;
import fatec.sjc.repository.LeilaoRepository;
import fatec.sjc.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
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
        leilao.setDataFim(leilaoDTO.getDataFim());
        leilao.setLocal(leilaoDTO.getLocal());
        leilao.setStatus(leilaoDTO.getStatus());

        List<Produto> produtos = produtoRepository.findByIds(leilaoDTO.getProdutosIds());
        leilao.setProdutos(produtos);

        EntidadeFinanceira entidadeFinanceira = entidadeFinanceiraRepository.findById(leilaoDTO.getInstituicaoFinanceiraId());
        leilao.setInstituicaoFinanceira(entidadeFinanceira);

        leilaoRepository.persist(leilao);
        return leilao;
    }
    public Produto detalharProduto(Long leilaoId, Long produtoId) {
        Leilao leilao = leilaoRepository.findById(leilaoId);

        if (leilao != null) {
            List<Produto> produtos = leilao.getProdutos();
            for (Produto produto : produtos) {
                if (produto.getId().equals(produtoId)) {
                    return produto;
                }
            }
        }
        return null;

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
        leilao.setDataFim(leilaoDTO.getDataFim());
        leilao.setLocal(leilaoDTO.getLocal());
        leilao.setStatus(leilaoDTO.getStatus());

        List<Produto> produtos = produtoRepository.findByIds(leilaoDTO.getProdutosIds());
        leilao.setProdutos(produtos);

        EntidadeFinanceira entidadeFinanceira = entidadeFinanceiraRepository.findById(leilaoDTO.getInstituicaoFinanceiraId());
        leilao.setInstituicaoFinanceira(entidadeFinanceira);

        leilaoRepository.persist(leilao);
    }

    @Transactional
    public void excluirLeilao(Long id) {
        leilaoRepository.deleteById(id);
    }
    
    public List<Leilao> listarLeiloesOrdenadosPorData() {
        List<Leilao> leiloesOrdenados = leilaoRepository.listAll()
                .stream()
                .sorted((leilao1, leilao2) -> leilao1.getDataOcorrencia().compareTo(leilao2.getDataOcorrencia()))
                .collect(Collectors.toList());
        return leiloesOrdenados;
    }
    @Transactional
    public void atualizarStatusLeiloes() {
        List<Leilao> leiloes = leilaoRepository.listAll();
        LocalDateTime currentDateTime = LocalDateTime.now();

        for (Leilao leilao : leiloes) {
            LocalDateTime dataOcorrencia = leilao.getDataOcorrencia();
            LocalDateTime dataFim = leilao.getDataFim();

            if (dataOcorrencia != null && dataFim != null) {
                if (dataOcorrencia.isAfter(currentDateTime)) {
                    leilao.setStatus("EM ABERTO");
                } else if (dataOcorrencia.isBefore(currentDateTime) && dataFim.isAfter(currentDateTime)) {
                    leilao.setStatus("EM ANDAMENTO");
                } else {
                    leilao.setStatus("FINALIZADO");
                }
            } else {
                Logger logger = Logger.getLogger(LeilaoService.class.getName());
                logger.warning("Leilao with ID " + leilao.getId() + " has null occurrence or end date.");
            }
        }

        leiloes.forEach(leilaoRepository::persist);
    }
    
    
    public DetalhesLeilaoDTO detalharLeilao(Long leilaoId) {
        Leilao leilao = leilaoRepository.findById(leilaoId);

        if (leilao != null) {
            List<Produto> produtosOrdenados = leilao.getProdutos().stream()
                    .sorted(Comparator.comparing(Produto::getNome))
                    .collect(Collectors.toList());

            DetalhesLeilaoDTO detalhesLeilaoDTO = new DetalhesLeilaoDTO();
            detalhesLeilaoDTO.setDataOcorrencia(leilao.getDataOcorrencia());
            detalhesLeilaoDTO.setDataFim(leilao.getDataFim());
            detalhesLeilaoDTO.setLocal(leilao.getLocal());
            detalhesLeilaoDTO.setStatus(leilao.getStatus());
            detalhesLeilaoDTO.setQuantidadeTotalProdutos(produtosOrdenados.size());
            detalhesLeilaoDTO.setProdutos(produtosOrdenados);

            EntidadeFinanceiraDTO entidadeFinanceiraDTO = new EntidadeFinanceiraDTO();
            //entidadeFinanceiraDTO.setId(leilao.getInstituicaoFinanceira().getId());
            entidadeFinanceiraDTO.setCnpj(leilao.getInstituicaoFinanceira().getCnpj());
            entidadeFinanceiraDTO.setNome(leilao.getInstituicaoFinanceira().getNome());
            entidadeFinanceiraDTO.setDetalhesContato(leilao.getInstituicaoFinanceira().getDetalhesContato());

            detalhesLeilaoDTO.setEntidadeFinanceira(entidadeFinanceiraDTO);

            return detalhesLeilaoDTO;
        }

        return null;
    }
    
    
}
