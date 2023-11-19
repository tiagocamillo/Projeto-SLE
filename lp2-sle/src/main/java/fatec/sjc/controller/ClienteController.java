package fatec.sjc.controller;

import fatec.sjc.dto.ClienteDTO;
import fatec.sjc.entity.Cliente;
import fatec.sjc.service.ClienteService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteController {

    private final ClienteService clienteService;

    @Inject
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @POST
    public Response salvarCliente(ClienteDTO clienteDTO) {
        try {
            // Tenta salvar o cliente
            Cliente savedCliente = clienteService.salvarCliente(clienteDTO);
            return Response.status(Response.Status.CREATED).entity(savedCliente).build();
        } catch (Exception e) {
            // Em caso de erro, retorna uma resposta com status de erro interno do servidor
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao salvar cliente: " + e.getMessage())
                    .build();
        }
    }

    @GET
    public Response listarClientes() {
        try {
            // Tenta obter a lista de clientes
            List<Cliente> clientes = clienteService.listarClientes();
            return Response.ok(clientes).build();
        } catch (Exception e) {
            // Em caso de erro, retorna uma resposta com status de erro interno do servidor
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar clientes: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        try {
            // Tenta buscar um cliente por ID
            Cliente cliente = clienteService.buscarPorId(id);
            return Response.ok(cliente).build();
        } catch (Exception e) {
            // Em caso de erro ou cliente não encontrado, retorna uma resposta com status não encontrado
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Cliente não encontrado com o ID " + id)
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarCliente(@PathParam("id") Long id, ClienteDTO clienteDTO) {
        try {
            // Tenta atualizar o cliente
            clienteService.atualizarCliente(id, clienteDTO);
            return Response.noContent().build();
        } catch (Exception e) {
            // Em caso de erro, retorna uma resposta com status de erro interno do servidor
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar cliente: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirCliente(@PathParam("id") Long id) {
        try {
            // Tenta excluir o cliente
            clienteService.excluirCliente(id);
            return Response.noContent().build();
        } catch (Exception e) {
            // Em caso de erro, retorna uma resposta com status de erro interno do servidor
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir cliente: " + e.getMessage())
                    .build();
        }
    }
}
