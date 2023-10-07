package fatec.sjc.controller;

import fatec.sjc.entity.Van;
import fatec.sjc.service.VanService;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/van")
@Produces(jakarta.ws.rs.core.MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VanController {

    @Inject
    VanService vanService;

    @POST
    public Response criarVan(@Valid Van van) {
        try {
            Van novaVan = vanService.criarVan(van);
            return Response.status(Response.Status.CREATED).entity(novaVan).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Dados inválidos. Verifique os campos obrigatórios.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao criar a van.").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarVan(@PathParam("id") Long id, @Valid Van van) {
        try {
            Van vanAtualizada = vanService.atualizarVan(id, van);
            if (vanAtualizada != null) {
                return Response.ok(vanAtualizada).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Van não encontrada.").build();
            }
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Dados inválidos. Verifique os campos obrigatórios.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao atualizar a van.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirVan(@PathParam("id") Long id) {
        try {
            vanService.deletarVan(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao excluir a van.").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarVanPorId(@PathParam("id") Long id) {
        Van van = vanService.buscarVanPorId(id);
        if (van != null) {
            return Response.ok(van).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Van não encontrada.").build();
        }
    }

    @GET
    public Response listarVans() {
        try {
            List<Van> vans = vanService.listarVans();
            return Response.ok(vans).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao listar as vans.").build();
        }
    }
}