package fatec.sjc.service;

import java.util.List;

import fatec.sjc.entity.Produto;
import fatec.sjc.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProdutoService {

    @Inject
	ProdutoRepository produtoRepository;
	
    @Transactional    
    public Produto criarProduto(Produto produto) {
    	produtoRepository.persist(produto);
        return produto;
	}
    
	
    @Transactional
    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
    	Produto produtoExistente = produtoRepository.findById(id);
        if (produtoExistente != null) {
        	produtoExistente.setNome(produtoAtualizado.getNome());
        	produtoExistente.setDescricao(produtoAtualizado.getDescricao());
        	produtoExistente.setTipoProduto(produtoAtualizado.getTipoProduto());
        }
        return produtoExistente;
    }
    
    @Transactional
    public void excluirProduto(Long id) {
        Produto produtoExistente = produtoExistente.findById(id);
        if (produtoExistente != null) {
        	produtoExistente.delete(produtoExistente);
        }
    }
    
    public Produto buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public List<Produto> listarTodosOsProdutos() {
        return produtoRepository.listAll();
    }
    
}
