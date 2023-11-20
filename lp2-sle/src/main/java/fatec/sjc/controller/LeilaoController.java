package fatec.sjc.controller;

import fatec.sjc.dto.DetalhesLeilaoDTO;
import fatec.sjc.dto.LeilaoDTO;
import fatec.sjc.entity.Leilao;
import fatec.sjc.entity.Produto;
import fatec.sjc.service.ExportacaoService;
import fatec.sjc.service.LeilaoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.util.List;

@Path("/leiloes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LeilaoController {

    private final LeilaoService leilaoService;
    private final ExportacaoService exportacaoService;

    @Inject
    public LeilaoController(LeilaoService leilaoService, ExportacaoService exportacaoService) {
        this.leilaoService = leilaoService;
        this.exportacaoService = exportacaoService;
    }

    @POST
    public Response salvarLeilao(LeilaoDTO leilaoDTO) {
        try {
            Leilao savedLeilao = leilaoService.salvarLeilao(leilaoDTO);
            return Response.status(Response.Status.CREATED).entity(savedLeilao).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao salvar leilão: " + e.getMessage())
                    .build();
        }
    }

    @GET
    public Response listarLeiloes() {
        try {
            leilaoService.atualizarStatusLeiloes();
            List<Leilao> leiloes = leilaoService.listarLeiloes();
            return Response.ok(leiloes).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar leilões: " + e.getMessage())
                    .build();
        }
    }
    @GET
    @Path("/detalhesEmAndamento")
    @Produces(MediaType.APPLICATION_JSON)

    public DetalhesLeilaoDTO obterDetalhesLeilaoEmAndamento() {
        leilaoService.atualizarStatusLeiloes();

        DetalhesLeilaoDTO detalhesLeilao = leilaoService.detalhesLeilaoEmAndamento();

        return detalhesLeilao;
    }
    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        try {
            Leilao leilao = leilaoService.buscarPorId(id);
            return Response.ok(leilao).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Leilão não encontrado com o ID " + id)
                    .build();
        }
    }

    @GET
    @Path("/{leilaoId}/produtos/{produtoId}")
    public Response detalharProduto(@PathParam("leilaoId") Long leilaoId, @PathParam("produtoId") Long produtoId) {
        leilaoService.atualizarStatusLeiloes();

        try {
            Produto produto = leilaoService.detalharProduto(leilaoId, produtoId);
            return Response.ok(produto).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Produto não encontrado no leilão com ID " + leilaoId)
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarLeilao(@PathParam("id") Long id, LeilaoDTO leilaoDTO) {
        leilaoService.atualizarStatusLeiloes();

        try {
            leilaoService.atualizarLeilao(leilaoDTO);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar leilão: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirLeilao(@PathParam("id") Long id) {
        try {
            leilaoService.excluirLeilao(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir leilão: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/listarOrdenadosPorData")
    public Response listarLeiloesOrdenadosPorData() {
        leilaoService.atualizarStatusLeiloes();
        try {
            List<Leilao> leiloesOrdenados = leilaoService.listarLeiloesOrdenadosPorData();
            return Response.ok(leiloesOrdenados).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar os leilões ordenados por data: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/exportar/{id}")
    public Response exportLeilaoData(@PathParam("id") Long id) {
        try {
            leilaoService.exportar(id);
            return Response.ok("Leilao data exported successfully.").build();
        } catch (IOException e) {
            e.printStackTrace(); // Log the exception or handle it appropriately
            return Response.serverError().entity("Failed to export leilao data.").build();
        }
    }
    @GET
    @Path("/{id}/produtos/filtrar")
    public Response filtrarProdutos(@PathParam("id") Long id,
                                    @QueryParam("minLanceInicial") double minLanceInicial,
                                    @QueryParam("maxLanceInicial") double maxLanceInicial,
                                    @QueryParam("minLanceTotal") double minLanceTotal,
                                    @QueryParam("maxLanceTotal") double maxLanceTotal,
                                    @QueryParam("palavraChave") String palavraChave,
                                    @QueryParam("tipoProduto") String tipoProduto) {
        try {
            List<Produto> produtosFiltrados = leilaoService.buscarProdutosPorFiltro(id, minLanceInicial, maxLanceInicial,
                    minLanceTotal, maxLanceTotal,
                    palavraChave, tipoProduto);
            return Response.ok(produtosFiltrados).build();
        } catch (Exception e) {
            // Em caso de erro, retorna uma resposta com status de erro interno do servidor
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao filtrar produtos do leilão: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{id}/detalhes-apos-termino")
    public Response detalhesLeilaoAposTermino(@PathParam("id") Long id) {
        try {
            DetalhesLeilaoDTO detalhesLeilaoDTO = leilaoService.buscarDetalhesLeilaoAposTermino(id);
            if (detalhesLeilaoDTO != null) {
                return Response.ok(detalhesLeilaoDTO).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Detalhes do leilão não encontrados.").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar detalhes do leilão após o término: " + e.getMessage())
                    .build();
        }
    }

}
