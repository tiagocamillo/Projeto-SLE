package fatec.sjc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import fatec.sjc.dto.DetalhesLeilaoDTO;
import fatec.sjc.dto.DetalhesLeilaoExport;
import fatec.sjc.dto.LeilaoDTO;
import fatec.sjc.entity.*;
import fatec.sjc.repository.EntidadeFinanceiraRepository;
import fatec.sjc.repository.LeilaoRepository;
import fatec.sjc.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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


    @Transactional
    public void exportar(Long id) throws IOException {
        Leilao leilao = leilaoRepository.findById(id);

        if (leilao != null) {
            DetalhesLeilaoExport detalhesLeilaoExport = convertLeilaoToDetalhesLeilaoExport(leilao);

            ObjectMapper objectMapper = new ObjectMapper();
            ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
            String json = objectWriter.writeValueAsString(detalhesLeilaoExport);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("detalhesLeilao" + id + ".det"))) {
                writer.write(json);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public DetalhesLeilaoExport convertLeilaoToDetalhesLeilaoExport(Leilao leilao) {
        if (leilao == null || leilao.getInstituicaoFinanceira() == null) {
            return null;
        }

        DetalhesLeilaoExport detalhesLeilaoExport = new DetalhesLeilaoExport();
        detalhesLeilaoExport.setLeilaoId(leilao.getId());
        detalhesLeilaoExport.setDataOcorrencia(leilao.getDataOcorrencia());
        detalhesLeilaoExport.setDataFim(leilao.getDataFim());
        detalhesLeilaoExport.setLocal(leilao.getLocal());
        detalhesLeilaoExport.setStatusLeilao(leilao.getStatus());
        detalhesLeilaoExport.setCnpjEntidadeFinanceira(leilao.getInstituicaoFinanceira().getCnpj());

        return detalhesLeilaoExport;
    }



    public List<Produto> buscarProdutosPorFiltro(Long idLeilao, double minLanceInicial, double maxLanceInicial,
            double minLanceTotal, double maxLanceTotal, String palavraChave,
            String tipoProduto) {
			Leilao leilao = leilaoRepository.findById(idLeilao);

			if (leilao != null) {
				List<Produto> produtos = leilao.getProdutos();

				return produtos.stream()
				.filter(produto -> produto.getLanceInicial() >= minLanceInicial && produto.getLanceInicial() <= maxLanceInicial)
				.filter(produto -> (produto.getLanceInicial() + produto.getLanceAdicional()) >= minLanceTotal &&
				(produto.getLanceInicial() + produto.getLanceAdicional()) <= maxLanceTotal)
				.filter(produto -> produto.getNome().toLowerCase().contains(palavraChave.toLowerCase()))
				.filter(produto -> tipoProduto.equals(produto.getTipo()))
				.collect(Collectors.toList());
			} else {
				return Collections.emptyList();
			}
	}

    public DetalhesLeilaoDTO buscarDetalhesLeilaoAposTermino(Long leilaoId) {
        Leilao leilao = leilaoRepository.findById(leilaoId);

        if (leilao != null && leilao.getStatus().equals("FINALIZADO")) {
            DetalhesLeilaoDTO detalhesLeilaoDTO = new DetalhesLeilaoDTO();
            detalhesLeilaoDTO.setDataOcorrencia(leilao.getDataOcorrencia());
            detalhesLeilaoDTO.setDataFim(leilao.getDataFim());
            detalhesLeilaoDTO.setLocal(leilao.getLocal());
            detalhesLeilaoDTO.setStatus(leilao.getStatus());

            List<Produto> produtos = leilao.getProdutos();
            detalhesLeilaoDTO.setProdutos(produtos);
            detalhesLeilaoDTO.setQuantidadeTotalProdutos(produtos.size());

            return detalhesLeilaoDTO;
        } else {
            return null;
        }
    }

    public DetalhesLeilaoDTO detalhesLeilaoEmAndamento() {
        LocalDateTime now = LocalDateTime.now();
        List<Leilao> leiloesEmAndamento = leilaoRepository.listAll()
                .stream()
                .filter(leilao -> leilao.getDataOcorrencia() != null && leilao.getDataFim() != null &&
                        leilao.getDataOcorrencia().isBefore(now) && leilao.getDataFim().isAfter(now))
                .collect(Collectors.toList());

        if (leiloesEmAndamento.isEmpty()) {
            return null;
        }

        Leilao leilaoEmAndamento = leiloesEmAndamento.get(0);

        DetalhesLeilaoDTO detalhesLeilao = new DetalhesLeilaoDTO();
        detalhesLeilao.setDataOcorrencia(leilaoEmAndamento.getDataOcorrencia());
        detalhesLeilao.setDataFim(leilaoEmAndamento.getDataFim());
        detalhesLeilao.setLocal(leilaoEmAndamento.getLocal());
        detalhesLeilao.setStatus(leilaoEmAndamento.getStatus());

        List<Produto> produtosLeilao = leilaoEmAndamento.getProdutos();
        detalhesLeilao.setProdutos(produtosLeilao);
        detalhesLeilao.setQuantidadeTotalProdutos(produtosLeilao.size());

        List<Produto> produtosVendidos = produtosLeilao
                .stream()
                .filter(produto -> produto.getStatus().equals("VENDIDO"))
                .collect(Collectors.toList());

        List<Produto> produtosNaoVendidos = produtosLeilao
                .stream()
                .filter(produto -> produto.getStatus().equals("NAO_VENDIDO"))
                .collect(Collectors.toList());

        List<Produto> produtosFinalizados = new ArrayList<>();
        List<Cliente> clientesGanhadores = new ArrayList<>();
        double valorLanceVencedor = 0.0;

        for (Produto produto : produtosVendidos) {
            List<LanceCliente> lances = produto.getLances();
            if (!lances.isEmpty()) {
                lances.sort((l1, l2) -> Double.compare(l2.getValor(), l1.getValor()));
                LanceCliente lanceVencedor = lances.get(0);

                valorLanceVencedor += lanceVencedor.getValor();
                produtosFinalizados.add(produto);
                clientesGanhadores.add(lanceVencedor.getCliente());
            }
        }

        detalhesLeilao.setProdutos(produtosFinalizados);
        detalhesLeilao.setClientesGanhadores(clientesGanhadores);
        detalhesLeilao.setValorLanceVencedor(valorLanceVencedor);

        return detalhesLeilao;
    }


}
