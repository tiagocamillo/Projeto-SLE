package fatec.sjc.controller;

import fatec.sjc.dto.EntidadeFinanceiraDTO;
import fatec.sjc.entity.EntidadeFinanceira;
import fatec.sjc.service.EntidadeFinanceiraService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/entidades-financeiras")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EntidadeFinanceiraController {

    private final EntidadeFinanceiraService entidadeFinanceiraService;

    @Inject
    public EntidadeFinanceiraController(EntidadeFinanceiraService entidadeFinanceiraService) {
        this.entidadeFinanceiraService = entidadeFinanceiraService;
    }

    @POST
    public Response salvarEntidadeFinanceira(EntidadeFinanceiraDTO entidadeFinanceiraDTO) {
        try {
            EntidadeFinanceira savedEntidadeFinanceira = entidadeFinanceiraService.salvarEntidadeFinanceira(entidadeFinanceiraDTO);
            return Response.status(Response.Status.CREATED).entity(savedEntidadeFinanceira).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao salvar entidade financeira: " + e.getMessage())
                    .build();
        }
    }

    @GET
    public Response listarEntidadesFinanceiras() {
        try {
            List<EntidadeFinanceira> entidadesFinanceiras = entidadeFinanceiraService.listarEntidadesFinanceiras();
            return Response.ok(entidadesFinanceiras).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar entidades financeiras: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        try {
            EntidadeFinanceira entidadeFinanceira = entidadeFinanceiraService.buscarPorId(id);
            return Response.ok(entidadeFinanceira).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Entidade financeira não encontrada com o ID " + id)
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarEntidadeFinanceira(@PathParam("id") Long id, EntidadeFinanceiraDTO entidadeFinanceiraDTO) {
        try {
            entidadeFinanceiraService.atualizarEntidadeFinanceira(id, entidadeFinanceiraDTO);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirEntidadeFinanceira(@PathParam("id") Long id) {
        try {
            entidadeFinanceiraService.excluirEntidadeFinanceira(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir entidade financeira: " + e.getMessage())
                    .build();
        }
    }
}
