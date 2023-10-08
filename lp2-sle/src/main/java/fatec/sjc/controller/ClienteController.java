package fatec.sjc.controller;

import fatec.sjc.entity.Cliente;
import fatec.sjc.service.ClienteService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/cliente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteController {

    @Inject
    ClienteService clienteService;

    @POST
    public Response criarCliente(@Valid Cliente cliente) {
        try {
            Cliente novoCliente = clienteService.criarCliente(cliente);
            return Response.status(Response.Status.CREATED).entity(novoCliente).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Dados inválidos. Verifique os campos obrigatórios.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao criar o cliente.").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarCliente(@PathParam("id") Long id, @Valid Cliente cliente) {
        try {
            Cliente clienteAtualizado = clienteService.atualizarCliente(id, cliente);
            if (clienteAtualizado != null) {
                return Response.ok(clienteAtualizado).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Cliente não encontrado.").build();
            }
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Dados inválidos. Verifique os campos obrigatórios.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao atualizar o cliente.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirCliente(@PathParam("id") Long id) {
        try {
            clienteService.excluirCliente(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao excluir o cliente.").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarClientePorId(@PathParam("id") Long id) {
        Cliente cliente = clienteService.buscarClientePorId(id);
        if (cliente != null) {
            return Response.ok(cliente).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Cliente não encontrado.").build();
        }
    }

    @GET
    public Response listarTodosOsClientes() {
        try {
            List<Cliente> clientes = clienteService.listarTodosOsClientes();
            return Response.ok(clientes).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao listar os clientes.").build();
        }
    }
}
