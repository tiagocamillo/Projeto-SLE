package fatec.sjc.controller;

import fatec.sjc.entity.Cliente;
import fatec.sjc.service.ClienteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteController {

    @Inject
    ClienteService clienteService;

    @GET
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    @GET
    @Path("/{id}")
    public Cliente buscarPorId(@PathParam("id") Long id) {
        return clienteService.buscarPorId(id);
    }

    @POST
    public Cliente salvarCliente(Cliente cliente) {
        return clienteService.salvarCliente(cliente);
    }

    @PUT
    @Path("/{id}")
    public void atualizarCliente(@PathParam("id") Long id, Cliente cliente) {
        Cliente clienteExistente = clienteService.buscarPorId(id);
        if (clienteExistente != null) {
            cliente.setId(id);
            clienteService.atualizarCliente(cliente);
        } else {
            throw new NotFoundException("Cliente não encontrado");
        }
    }

    @DELETE
    @Path("/{id}")
    public void excluirCliente(@PathParam("id") Long id) {
        clienteService.excluirCliente(id);
    }
}