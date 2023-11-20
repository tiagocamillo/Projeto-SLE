package fatec.sjc.controller;

import fatec.sjc.dto.MotoDTO;
import fatec.sjc.entity.Moto;
import fatec.sjc.service.MotoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/motos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MotoController {

    private final MotoService motoService;

    @Inject
    public MotoController(MotoService motoService) {
        this.motoService = motoService;
    }

    @POST
    public Response salvarMoto(MotoDTO motoDTO) {
        try {
            Moto savedMoto = motoService.salvarMoto(motoDTO);
            return Response.status(Response.Status.CREATED).entity(savedMoto).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao salvar moto: " + e.getMessage())
                    .build();
        }
    }

    @GET
    public Response listarMotos() {
        try {
            List<Moto> motos = motoService.listarMotos();
            return Response.ok(motos).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar motos: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        try {
            Moto moto = motoService.buscarPorId(id);
            return Response.ok(moto).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Moto não encontrada com o ID " + id)
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarMoto(@PathParam("id") Long id, MotoDTO motoDTO) {
        try {
            motoService.atualizarMoto(motoDTO);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar moto: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirMoto(@PathParam("id") Long id) {
        try {
            motoService.excluirMoto(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir moto: " + e.getMessage())
                    .build();
        }
    }
}
