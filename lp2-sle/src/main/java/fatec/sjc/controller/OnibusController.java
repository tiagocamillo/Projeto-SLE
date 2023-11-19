package fatec.sjc.controller;

import fatec.sjc.dto.OnibusDTO;
import fatec.sjc.entity.Onibus;
import fatec.sjc.service.OnibusService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/onibus")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OnibusController {

    private final OnibusService onibusService;

    @Inject
    public OnibusController(OnibusService onibusService) {
        this.onibusService = onibusService;
    }

    @POST
    public Response salvarOnibus(OnibusDTO onibusDTO) {
        try {
            Onibus savedOnibus = onibusService.salvarOnibus(onibusDTO);
            return Response.status(Response.Status.CREATED).entity(savedOnibus).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao salvar ônibus: " + e.getMessage())
                    .build();
        }
    }

    @GET
    public Response listarOnibus() {
        try {
            List<Onibus> onibusList = onibusService.listarOnibus();
            return Response.ok(onibusList).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar ônibus: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        try {
            Onibus onibus = onibusService.buscarPorId(id);
            return Response.ok(onibus).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Ônibus não encontrado com o ID " + id)
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarOnibus(@PathParam("id") Long id, OnibusDTO onibusDTO) {
        try {
            onibusService.atualizarOnibus(onibusDTO);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar ônibus: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirOnibus(@PathParam("id") Long id) {
        try {
            onibusService.excluirOnibus(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir ônibus: " + e.getMessage())
                    .build();
        }
    }
}
