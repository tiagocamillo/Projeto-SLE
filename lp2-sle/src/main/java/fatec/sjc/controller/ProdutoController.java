package fatec.sjc.controller;
import fatec.sjc.entity.Produto;
import fatec.sjc.service.ProdutoService;
import jakarta.ws.rs.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoController {

    @Inject
    ProdutoService produtoService;

    @GET
    public List<Produto> listarProdutos() {
        return produtoService.listarProdutos();
    }

    @GET
    @Path("/{id}")
    public Produto buscarPorId(@PathParam("id") Long id) {
        return produtoService.buscarPorId(id);
    }

    @POST
    public Produto salvarProduto(Produto produto) {
        return produtoService.salvarProduto(produto);
    }

    @PUT
    @Path("/{id}")
    public void atualizarProduto(@PathParam("id") Long id, Produto produto) {
        Produto produtoExistente = produtoService.buscarPorId(id);
        if (produtoExistente != null) {
            produto.getId();
            produtoService.atualizarProduto(produto);
        } else {
            throw new NotFoundException("Produto não encontrado");
        }
    }

    @DELETE
    @Path("/{id}")
    public void excluirProduto(@PathParam("id") Long id) {
        produtoService.excluirProduto(id);
    }
}