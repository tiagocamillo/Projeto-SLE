package fatec.sjc.controller;
import java.util.List;

import fatec.sjc.dto.ProdutoDTO;
import fatec.sjc.entity.LanceCliente;
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
    
    
    @GET
    @Path("/{id}/lances")
    public List<LanceCliente> detalharHistoricoLances(@PathParam("id") Long idProduto) {
        return produtoService.detalharHistoricoLances(idProduto);
    }
    
}