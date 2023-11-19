package fatec.sjc.controller;

import java.util.List;

import fatec.sjc.dto.DetalhesLeilaoDTO;
import fatec.sjc.dto.LeilaoDTO;
import fatec.sjc.entity.Leilao;
import fatec.sjc.entity.Produto;
import fatec.sjc.service.LeilaoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
@Path("/leiloes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LeilaoController {

    private final LeilaoService leilaoService;

    @Inject
    public LeilaoController(LeilaoService leilaoService) {
        this.leilaoService = leilaoService;
    }

    @POST
    public Leilao salvarLeilao(LeilaoDTO leilaoDTO) {
        return leilaoService.salvarLeilao(leilaoDTO);
    }

    @GET
    public List<Leilao> listarLeiloes() {
        leilaoService.atualizarStatusLeiloes();

        return leilaoService.listarLeiloes();
    }

    @GET
    @Path("/{id}")
    public Leilao buscarPorId(@PathParam("id") Long id) {
        return leilaoService.buscarPorId(id);
    }
    @GET
    @Path("/{leilaoId}/produtos/{produtoId}")
    public Produto detalharProduto(@PathParam("leilaoId") Long leilaoId, @PathParam("produtoId") Long produtoId) {
        return leilaoService.detalharProduto(leilaoId, produtoId);
    }
    @PUT
    @Path("/{id}")
    public void atualizarLeilao(@PathParam("id") Long id, LeilaoDTO leilaoDTO) {
        leilaoService.atualizarLeilao(leilaoDTO);
    }

    @DELETE
    @Path("/{id}")
    public void excluirLeilao(@PathParam("id") Long id) {
        leilaoService.excluirLeilao(id);
    }
    
    @GET
    @Path("/listarOrdenadosPorData")
    public Response listarLeiloesOrdenadosPorData() {
        try {
            List<Leilao> leiloesOrdenados = leilaoService.listarLeiloesOrdenadosPorData();
            return Response.ok(leiloesOrdenados).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao listar os leilões.").build();
        }
    } 
    
    @GET
    @Path("/{id}/detalhes")
    public DetalhesLeilaoDTO detalharLeilao(@PathParam("id") Long id) {
        return leilaoService.detalharLeilao(id);
    }
    
    @GET
    @Path("/{id}/produtos/filtrar")
    public List<Produto> filtrarProdutos(@PathParam("id") Long id,
                                         @QueryParam("minLanceInicial") double minLanceInicial,
                                         @QueryParam("maxLanceInicial") double maxLanceInicial,
                                         @QueryParam("minLanceTotal") double minLanceTotal,
                                         @QueryParam("maxLanceTotal") double maxLanceTotal,
                                         @QueryParam("palavraChave") String palavraChave,
                                         @QueryParam("tipoProduto") String tipoProduto) {
        return leilaoService.buscarProdutosPorFiltro(id, minLanceInicial, maxLanceInicial,
                                                     minLanceTotal, maxLanceTotal, palavraChave,
                                                     tipoProduto);
    }
    
    @GET
    @Path("/{id}/detalhes-apos-termino")
    public Response detalhesLeilaoAposTermino(@PathParam("id") Long id) {
        DetalhesLeilaoDTO detalhesLeilaoDTO = leilaoService.buscarDetalhesLeilaoAposTermino(id);

        if (detalhesLeilaoDTO != null) {
            return Response.ok(detalhesLeilaoDTO).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Detalhes do leilão não encontrados.").build();
        }
    }
    
}