package fatec.sjc.controller;
import fatec.sjc.dto.ProdutoDTO;
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

    private final ProdutoService produtoService;

    @Inject
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @POST
    public Produto salvarProduto(ProdutoDTO produtoDTO) {
        return produtoService.salvarProduto(produtoDTO);
    }

    @GET
    public List<Produto> listarProdutos() {
        return produtoService.listarProdutos();
    }

    @GET
    @Path("/{id}")
    public Produto buscarPorId(@PathParam("id") Long id) {
        return produtoService.buscarPorId(id);
    }

    @PUT
    @Path("/{id}")
    public void atualizarProduto(@PathParam("id") Long id, ProdutoDTO produtoDTO) {
        produtoService.atualizarProduto(produtoDTO);
    }

    @DELETE
    @Path("/{id}")
    public void excluirProduto(@PathParam("id") Long id) {
        produtoService.excluirProduto(id);
    }
}