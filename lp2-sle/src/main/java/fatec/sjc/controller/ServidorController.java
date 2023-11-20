package fatec.sjc.controller;

import fatec.sjc.dto.ServidorDTO;
import fatec.sjc.entity.Servidor;
import fatec.sjc.service.ServidorService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/servidores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServidorController {

    private final ServidorService servidorService;

    @Inject
    public ServidorController(ServidorService servidorService) {
        this.servidorService = servidorService;
    }

    @POST
    public Response salvarServidor(ServidorDTO servidorDTO) {
        try {
            Servidor savedServidor = servidorService.salvarServidor(servidorDTO);
            return Response.status(Response.Status.CREATED).entity(savedServidor).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao salvar servidor: " + e.getMessage())
                    .build();
        }
    }

    @GET
    public Response listarServidores() {
        try {
            List<Servidor> servidores = servidorService.listarServidores();
            return Response.ok(servidores).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar servidores: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        try {
            Servidor servidor = servidorService.buscarPorId(id);
            return Response.ok(servidor).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Servidor não encontrado com o ID " + id)
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarServidor(@PathParam("id") Long id, ServidorDTO servidorDTO) {
        try {
            servidorService.atualizarServidor(servidorDTO);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar servidor: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirServidor(@PathParam("id") Long id) {
        try {
            servidorService.excluirServidor(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir servidor: " + e.getMessage())
                    .build();
        }
    }
}
