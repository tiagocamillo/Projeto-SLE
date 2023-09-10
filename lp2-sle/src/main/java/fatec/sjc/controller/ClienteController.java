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

    @POST
    public Cliente criarCliente(Cliente cliente) {
        return clienteService.criarCliente(cliente);
    }

    @PUT
    @Path("/{id}")
    public Cliente atualizarCliente(@PathParam("id") Long id, Cliente cliente) {
        return clienteService.atualizarCliente(id, cliente);
    }

    @DELETE
    @Path("/{id}")
    public void excluirCliente(@PathParam("id") Long id) {
        clienteService.excluirCliente(id);
    }

    @GET
    @Path("/{id}")
    public Cliente buscarClientePorId(@PathParam("id") Long id) {
        return clienteService.buscarClientePorId(id);
    }

    @GET
    public List<Cliente> listarTodosOsClientes() {
        return clienteService.listarTodosOsClientes();
    }
}
