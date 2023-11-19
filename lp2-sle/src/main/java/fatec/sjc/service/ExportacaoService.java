package fatec.sjc.service;

import fatec.sjc.dto.DetalhesLeilaoExport;
import fatec.sjc.dto.LanceClienteDTO;
import fatec.sjc.dto.ProdutoExportDTO;
import fatec.sjc.entity.Cliente;
import fatec.sjc.entity.LanceCliente;
import fatec.sjc.entity.Leilao;
import fatec.sjc.entity.Produto;
import fatec.sjc.repository.ClienteRepository;
import fatec.sjc.repository.LeilaoRepository;
import fatec.sjc.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class ExportacaoService {

    @Inject
    LeilaoRepository leilaoRepository;

    @Inject
    ProdutoRepository produtoRepository;

    @Inject
    ClienteRepository clienteRepository;

    @Transactional
    public Optional<DetalhesLeilaoExport> exportarDetalhesLeilao(Long leilaoId) {
        Leilao leilao = leilaoRepository.findById(leilaoId);

        if (leilao == null) {
            return Optional.empty();
        }

        DetalhesLeilaoExport detalhesLeilaoExport = new DetalhesLeilaoExport();
        detalhesLeilaoExport.setLeilaoId(leilao.getId());
        detalhesLeilaoExport.setDataOcorrencia(leilao.getDataOcorrencia());
        detalhesLeilaoExport.setDataFim(leilao.getDataFim());
        detalhesLeilaoExport.setLocal(leilao.getLocal());
        detalhesLeilaoExport.setStatusLeilao(leilao.getStatus());

//        detalhesLeilaoExport.setCnpjEntidadeFinanceira(leilao.getInstituicaoFinanceira().getCnpj());

        detalhesLeilaoExport.setProdutos(leilao.getProdutos().stream()
                .map(this::mapProdutoToDTO)
                .collect(Collectors.toList()));

        return Optional.of(detalhesLeilaoExport);
    }

    private ProdutoExportDTO mapProdutoToDTO(Produto produto) {
        ProdutoExportDTO produtoDTO = new ProdutoExportDTO();
        produtoDTO.setProdutoId(produto.getId());
        produtoDTO.setNomeProduto(produto.getNome());
        produtoDTO.setDescricao(produto.getDescricao());
        produtoDTO.setStatusProduto(produto.getStatus());
        produtoDTO.setTipoProduto(produto.getTipo());
        produtoDTO.setLanceInicial(produto.getLanceInicial());
        produtoDTO.setLanceAdicional(produto.getLanceAdicional());

        produtoDTO.setLances(produto.getLances().stream()
                .map(this::mapLanceClienteToDTO)
                .collect(Collectors.toList()));

        return produtoDTO;
    }

    private LanceClienteDTO mapLanceClienteToDTO(LanceCliente lance) {
        LanceClienteDTO lanceDTO = new LanceClienteDTO();
        lanceDTO.setValor(lance.getValor());
        lanceDTO.setIdProduto(lance.getProduto().getId());
        lanceDTO.setIdCliente(lance.getCliente().getId());
        lanceDTO.setDataHoraLance(lance.getDataHoraLance());
        return lanceDTO;
    }

    @Transactional
    public Cliente findClienteById(Long clienteId) {
        return clienteRepository.findById(clienteId);
    }

    @Transactional
    public Produto findProdutoById(Long produtoId) {
        return produtoRepository.findById(produtoId);
    }
}