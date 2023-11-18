package fatec.sjc.service;

import fatec.sjc.dto.ProdutoDTO;
import fatec.sjc.entity.Leilao;
import fatec.sjc.entity.Produto;
import fatec.sjc.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

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

        // Verifica se o ID do leilão está presente no DTO
        if (produtoDTO.getLeilao() != null && produtoDTO.getLeilao().getId() != null) {
            Long leilaoId = produtoDTO.getLeilao().getId();

            Leilao leilao = new Leilao();
            leilao.setId(leilaoId);
            produto.setLeilao(leilao);
        }

        produtoRepository.persist(produto);

        return produto;
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
        produto.setLeilao(produtoDTO.getLeilao());
        produtoRepository.persist(produto);
    }

    @Transactional
    public void excluirProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}