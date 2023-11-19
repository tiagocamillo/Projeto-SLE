package fatec.sjc.controller;

import fatec.sjc.dto.ProdutoDTO;
import fatec.sjc.entity.LanceCliente;
import fatec.sjc.entity.Produto;
import fatec.sjc.service.ProdutoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
    public Response salvarProduto(ProdutoDTO produtoDTO) {
        try {
            Produto savedProduto = produtoService.salvarProduto(produtoDTO);
            return Response.status(Response.Status.CREATED).entity(savedProduto).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao salvar produto: " + e.getMessage())
                    .build();
        }
    }

    @GET
    public Response listarProdutos() {
        try {
            List<Produto> produtos = produtoService.listarProdutos();
            return Response.ok(produtos).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar produtos: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        try {
            Produto produto = produtoService.buscarPorId(id);
            return Response.ok(produto).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Produto não encontrado com o ID " + id)
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarProduto(@PathParam("id") Long id, ProdutoDTO produtoDTO) {
        try {
            produtoService.atualizarProduto(produtoDTO);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar produto: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirProduto(@PathParam("id") Long id) {
        try {
            produtoService.excluirProduto(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir produto: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{id}/lances")
    public Response detalharHistoricoLances(@PathParam("id") Long idProduto) {
        try {
            List<LanceCliente> historicoLances = produtoService.detalharHistoricoLances(idProduto);
            return Response.ok(historicoLances).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao obter o histórico de lances do produto: " + e.getMessage())
                    .build();
        }
    }
}
