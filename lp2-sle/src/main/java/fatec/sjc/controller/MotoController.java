package fatec.sjc.controller;

import fatec.sjc.entity.Moto;
import fatec.sjc.service.MotoService;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/moto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MotoController {

    @Inject
    MotoService motoService;

    @POST
    public Response criarMoto(@Valid Moto moto) {
        try {
            Moto novaMoto = motoService.criarMoto(moto);
            return Response.status(Response.Status.CREATED).entity(novaMoto).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Dados inválidos. Verifique os campos obrigatórios.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao criar a moto.").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarMoto(@PathParam("id") Long id, @Valid Moto moto) {
        try {
            Moto motoAtualizada = motoService.atualizarMoto(id, moto);
            if (motoAtualizada != null) {
                return Response.ok(motoAtualizada).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Moto não encontrada.").build();
            }
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Dados inválidos. Verifique os campos obrigatórios.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao atualizar a moto.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirMoto(@PathParam("id") Long id) {
        try {
            motoService.excluirMoto(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao excluir a moto.").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarMotoPorId(@PathParam("id") Long id) {
        Moto moto = motoService.buscarMotoPorId(id);
        if (moto != null) {
            return Response.ok(moto).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Moto não encontrada.").build();
        }
    }

    @GET
    public Response listarMotos() {
        try {
            List<Moto> motos = motoService.listarTodasAsMotos();
            return Response.ok(motos).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao listar as motos.").build();
        }
    }
}