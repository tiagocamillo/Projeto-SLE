package fatec.sjc.controller;

import fatec.sjc.entity.Impressora;
import fatec.sjc.service.ImpressoraService;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Path;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.validation.Valid;

import java.util.List;

@Path("/impressora")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ImpressoraController {

    @Inject
    ImpressoraService impressoraService;

    @POST
    public Response criarImpressora(@Valid Impressora impressora) {
        try {
            Impressora novaImpressora = impressoraService.criarImpressora(impressora);
            return Response.status(Response.Status.CREATED).entity(novaImpressora).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Dados inválidos. Verifique os campos obrigatórios.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao criar a impressora.").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarImpressora(@PathParam("id") Long id, @Valid Impressora impressora) {
        try {
            Impressora impressoraAtualizada = impressoraService.atualizarImpressora(id, impressora);
            if (impressoraAtualizada != null) {
                return Response.ok(impressoraAtualizada).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Impressora não encontrada.").build();
            }
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Dados inválidos. Verifique os campos obrigatórios.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao atualizar a impressora.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirImpressora(@PathParam("id") Long id) {
        try {
            impressoraService.deletarImpressora(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao excluir a impressora.").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarImpressoraPorId(@PathParam("id") Long id) {
        Impressora impressora = impressoraService.buscarImpressoraPorId(id);
        if (impressora != null) {
            return Response.ok(impressora).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Impressora não encontrada.").build();
        }
    }

    @GET
    public Response listarImpressoras() {
        try {
            List<Impressora> impressoras = impressoraService.listarImpressoras();
            return Response.ok(impressoras).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao listar as impressoras.").build();
        }
    }
}
