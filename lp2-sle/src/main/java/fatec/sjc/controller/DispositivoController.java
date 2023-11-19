package fatec.sjc.controller;

import fatec.sjc.dto.DispositivoDTO;
import fatec.sjc.entity.Dispositivo;
import fatec.sjc.service.DispositivoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/dispositivos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DispositivoController {

    private final DispositivoService dispositivoService;

    @Inject
    public DispositivoController(DispositivoService dispositivoService) {
        this.dispositivoService = dispositivoService;
    }

    @POST
    public Response salvarDispositivo(DispositivoDTO dispositivoDTO) {
        try {
            // Tenta salvar um dispositivo
            Dispositivo savedDispositivo = dispositivoService.salvarDispositivo(dispositivoDTO);
            return Response.status(Response.Status.CREATED).entity(savedDispositivo).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao salvar dispositivo: " + e.getMessage())
                    .build();
        }
    }

    @GET
    public Response listarDispositivos() {
        try {
            List<Dispositivo> dispositivos = dispositivoService.listarDispositivos();
            return Response.ok(dispositivos).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar dispositivos: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        try {
            // Tenta buscar um dispositivo por ID
            Dispositivo dispositivo = dispositivoService.buscarPorId(id);
            return Response.ok(dispositivo).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Dispositivo não encontrado com o ID " + id)
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarDispositivo(@PathParam("id") Long id, DispositivoDTO dispositivoDTO) {
        try {
            dispositivoDTO.setId(id);
            dispositivoService.atualizarDispositivo(dispositivoDTO);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar dispositivo: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirDispositivo(@PathParam("id") Long id) {
        try {
            dispositivoService.excluirDispositivo(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir dispositivo: " + e.getMessage())
                    .build();
        }
    }
}
