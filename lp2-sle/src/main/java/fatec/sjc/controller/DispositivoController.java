package fatec.sjc.controller;

import fatec.sjc.DTO.DispositivoDTO;
import fatec.sjc.entity.Dispositivo;
import fatec.sjc.service.DispositivoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/dispositivo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DispositivoController {

    @Inject
    DispositivoService dispositivoService;

    @POST
    public Response criarDispositivo(@Valid DispositivoDTO dispositivoDTO) {
        try {
            Dispositivo novoDispositivo = dispositivoService.criarDispositivo(dispositivoDTO);
            return Response.status(Response.Status.CREATED).entity(novoDispositivo).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Dados inválidos. Verifique os campos obrigatórios.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao criar o dispositivo.").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarDispositivo(@PathParam("id") Long id, @Valid DispositivoDTO dispositivoDTO) {
        try {
            Dispositivo dispositivoAtualizado = dispositivoService.atualizarDispositivo(id, dispositivoDTO);
            if (dispositivoAtualizado != null) {
                return Response.ok(dispositivoAtualizado).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Dispositivo não encontrado.").build();
            }
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Dados inválidos. Verifique os campos obrigatórios.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao atualizar o dispositivo.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirDispositivo(@PathParam("id") Long id) {
        try {
            dispositivoService.excluirDispositivo(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao excluir o dispositivo.").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarDispositivoPorId(@PathParam("id") Long id) {
        Dispositivo dispositivo = dispositivoService.buscarDispositivoPorId(id);
        if (dispositivo != null) {
            return Response.ok(dispositivo).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Dispositivo não encontrado.").build();
        }
    }

    @GET
    public Response listarTodosOsDispositivos() {
        try {
            List<Dispositivo> dispositivos = dispositivoService.listarTodosOsDispositivos();
            return Response.ok(dispositivos).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao listar os dispositivos.").build();
        }
    }
}
