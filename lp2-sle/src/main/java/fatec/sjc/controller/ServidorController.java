package fatec.sjc.controller;

import fatec.sjc.entity.Servidor;
import fatec.sjc.service.ServidorService;
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

@Path("/servidor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServidorController {

    @Inject
    ServidorService servidorService;

    @POST
    public Response criarServidor(@Valid Servidor servidor) {
        try {
            Servidor novoServidor = servidorService.criarServidor(servidor);
            return Response.status(Response.Status.CREATED).entity(novoServidor).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Dados inválidos. Verifique os campos obrigatórios.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao criar o servidor.").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarServidor(@PathParam("id") Long id, @Valid Servidor servidor) {
        try {
            Servidor servidorAtualizado = servidorService.atualizarServidor(id, servidor);
            if (servidorAtualizado != null) {
                return Response.ok(servidorAtualizado).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Servidor não encontrado.").build();
            }
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Dados inválidos. Verifique os campos obrigatórios.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao atualizar o servidor.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirServidor(@PathParam("id") Long id) {
        try {
            servidorService.excluirServidor(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao excluir o servidor.").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarServidorPorId(@PathParam("id") Long id) {
        Servidor servidor = servidorService.buscarServidorPorId(id);
        if (servidor != null) {
            return Response.ok(servidor).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Servidor não encontrado.").build();
        }
    }

    @GET
    public Response listarServidores() {
        try {
            List<Servidor> servidores = servidorService.listarTodosOsServidores();
            return Response.ok(servidores).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao listar os servidores.").build();
        }
    }
}
