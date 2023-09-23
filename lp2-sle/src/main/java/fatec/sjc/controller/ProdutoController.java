package fatec.sjc.controller;

import java.util.List;

import fatec.sjc.entity.Produto;
import fatec.sjc.service.ProdutoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/produto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoController {

	@Inject
	ProdutoService produtoService;

    @POST
    public Produto criarProduto(Produto produto) {
        return produtoService.criarProduto(produto);
    }

    @PUT
    @Path("/{id}")
    public Produto atualizarProduto(@PathParam("id") Long id, Produto produto) {
        return produtoService.atualizarProduto(id, produto);
    }

    @DELETE
    @Path("/{id}")
    public void excluirProduto(@PathParam("id") Long id) {
    	produtoService.excluirProduto(id);
    }

    @GET
    @Path("/{id}")
    public Produto buscarProdutoPorId(@PathParam("id") Long id) {
        return produtoService.buscarProdutoPorId(id);
    }

    @GET
    public List<Produto> listarTodosOsProdutos() {
        return produtoService.listarTodosOsProdutos();
    }
	
}
