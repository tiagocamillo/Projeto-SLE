package fatec.sjc.service;

import fatec.sjc.dto.ProdutoDTO;
import fatec.sjc.entity.Leilao;
import fatec.sjc.entity.Produto;
import fatec.sjc.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Inject
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public Produto salvarProduto(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setStatus(produtoDTO.getStatus());
        produto.setTipo(produtoDTO.getTipo());
        produto.setLanceInicial(produtoDTO.getLanceInicial());
        produto.setLanceAdicional(produtoDTO.getLanceAdicional());

        produtoRepository.persist(produto);

        return produto;
    }

    public ProdutoDTO detalharProduto(Long id) {
        Optional<Produto> produtoOptional = Optional.ofNullable(produtoRepository.findById(id));

        if (produtoOptional.isPresent()) {
            Produto produto = produtoOptional.get();
            Leilao leilaoAssociado = produto.getLeilao();

            ProdutoDTO produtoDTO = new ProdutoDTO();
            produtoDTO.setNome(produto.getNome());
            produtoDTO.setDescricao(produto.getDescricao());
            produtoDTO.setStatus(produto.getStatus());
            produtoDTO.setTipo(produto.getTipo());
            produtoDTO.setLanceInicial(produto.getLanceInicial());
            produtoDTO.setLanceAdicional(produto.getLanceAdicional());

//            if (leilaoAssociado != null) {
//                produtoDTO.setLeilaoId(leilaoAssociado.getId());
//                produtoDTO.setDataOcorrenciaLeilao(leilaoAssociado.getDataOcorrencia());
//                produtoDTO.setLocalLeilao(leilaoAssociado.getLocal());
//                produtoDTO.setStatusLeilao(leilaoAssociado.getStatus());
//            } else {
//                produtoDTO.setLeilaoId(null);
//                produtoDTO.setDataOcorrenciaLeilao(null);
//                produtoDTO.setLocalLeilao(null);
//                produtoDTO.setStatusLeilao(null);
//            }

            return produtoDTO;
        } else {
            throw new RuntimeException("Produto não encontrado com o ID: " + id);
        }
    }
    public List<Produto> listarProdutos() {
        return produtoRepository.listAll();
    }
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    @Transactional
    public void atualizarProduto(ProdutoDTO produtoDTO) {
    	Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setStatus(produtoDTO.getStatus());
        produto.setTipo(produtoDTO.getTipo());
        produto.setLanceInicial(produtoDTO.getLanceInicial());
        produto.setLanceAdicional(produtoDTO.getLanceAdicional());
        
        produtoRepository.persist(produto);
        
    }

    @Transactional
    public void excluirProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}
