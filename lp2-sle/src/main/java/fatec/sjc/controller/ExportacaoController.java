package fatec.sjc.controller;

import fatec.sjc.dto.DetalhesLeilaoExport;
import fatec.sjc.service.ExportacaoService;
import jakarta.ws.rs.Path;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@Path("/api/exportacao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExportacaoController {

    @Inject
    ExportacaoService exportacaoService;

    @GET
    @Path("/leilao/{leilaoId}")
    public Response exportarDetalhesLeilao(@PathParam("leilaoId") Long leilaoId) {
        try {
            Optional<DetalhesLeilaoExport> detalhesLeilaoExport = exportacaoService.exportarDetalhesLeilao(leilaoId);
            exportacaoService.atualizarStatusLeiloes();
            if (detalhesLeilaoExport.isPresent()) {
                return Response.ok(detalhesLeilaoExport).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Leilao não encontrado").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao exportar detalhes do leilao: " + e.getMessage())
                    .build();
        }
    }
}
