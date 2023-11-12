package fatec.sjc.controller;

import fatec.sjc.DTO.OnibusDTO;
import fatec.sjc.entity.Onibus;
import fatec.sjc.service.OnibusService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.ws.rs.Path;
import jakarta.inject.Inject;
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

@Path("/onibus")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OnibusController {

    @Inject
    OnibusService onibusService;

    @POST
    public Response criarOnibus(@Valid OnibusDTO onibusDTO) {
        try {
            OnibusDTO novoOnibus = onibusService.criarOnibus(onibusDTO);
            return Response.status(Response.Status.CREATED).entity(novoOnibus).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Dados inválidos. Verifique os campos obrigatórios.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao criar o ônibus.").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarOnibus(@PathParam("id") Long id, @Valid OnibusDTO onibusDTO) {
        try {
            OnibusDTO onibusAtualizado = onibusService.atualizarOnibus(id, onibusDTO);
            if (onibusAtualizado != null) {
                return Response.ok(onibusAtualizado).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Ônibus não encontrado.").build();
            }
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Dados inválidos. Verifique os campos obrigatórios.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao atualizar o ônibus.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirOnibus(@PathParam("id") Long id) {
        try {
            onibusService.excluirOnibus(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao excluir o ônibus.").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarOnibusPorId(@PathParam("id") Long id) {
        OnibusDTO onibus = onibusService.buscarOnibusPorId(id);
        if (onibus != null) {
            return Response.ok(onibus).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Ônibus não encontrado.").build();
        }
    }

    @GET
    public Response listarOnibus() {
        try {
            List<OnibusDTO> onibus = onibusService.listarTodosOsOnibus();
            return Response.ok(onibus).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao listar os ônibus.").build();
        }
    }
}