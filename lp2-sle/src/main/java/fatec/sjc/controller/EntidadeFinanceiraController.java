package fatec.sjc.controller;

import fatec.sjc.entity.EntidadeFinanceira;
import fatec.sjc.service.EntidadesFinanceiraService;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/entidade-financeira")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EntidadeFinanceiraController {

    @Inject
    EntidadesFinanceiraService entidadesFinanceiraService;

    @POST
    public Response criarEntidadesFinanceira(@Valid EntidadeFinanceira entidadeFinanceira) {
        try {
            EntidadeFinanceira novaEntidadeFinanceira = entidadesFinanceiraService.criarEntidadeFinanceira(entidadeFinanceira);
            return Response.status(Response.Status.CREATED).entity(novaEntidadeFinanceira).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Dados inválidos. Verifique os campos obrigatórios.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao criar a entidade financeira.").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarEntidadesFinanceira(@PathParam("id") Long id, @Valid EntidadeFinanceira entidadeFinanceira) {
        try {
            EntidadeFinanceira entidadeFinanceiraAtualizada = entidadesFinanceiraService.atualizarEntidadeFinanceira(id, entidadeFinanceira);
            if (entidadeFinanceiraAtualizada != null) {
                return Response.ok(entidadeFinanceiraAtualizada).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Entidade financeira não encontrada.").build();
            }
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Dados inválidos. Verifique os campos obrigatórios.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao atualizar a entidade financeira.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirEntidadesFinanceira(@PathParam("id") Long id) {
        try {
            entidadesFinanceiraService.excluirEntidadeFinanceira(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao excluir a entidade financeira.").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarEntidadesFinanceiraPorId(@PathParam("id") Long id) {
        EntidadeFinanceira entidadeFinanceira = entidadesFinanceiraService.buscarEntidadeFinanceiraPorId(id);
        if (entidadeFinanceira != null) {
            return Response.ok(entidadeFinanceira).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Entidade financeira não encontrada.").build();
        }
    }

    @GET
    public Response listarTodasAsEntidadesFinanceiras() {
        try {
            List<EntidadeFinanceira> entidadesFinanceiras = entidadesFinanceiraService.listarTodasAsEntidadesFinanceiras();
            return Response.ok(entidadesFinanceiras).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao listar as entidades financeiras.").build();
        }
    }
}
