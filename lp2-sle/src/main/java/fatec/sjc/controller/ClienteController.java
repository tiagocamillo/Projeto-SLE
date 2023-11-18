package fatec.sjc.controller;

import fatec.sjc.dto.ClienteDTO;
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

    private final ClienteService clienteService;

    @Inject
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @POST
    public Cliente salvarCliente(ClienteDTO clienteDTO) {
        return clienteService.salvarCliente(clienteDTO);
    }

    @GET
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    @GET
    @Path("/{id}")
    public Cliente buscarPorId(@PathParam("id") Long id) {
        return clienteService.buscarPorId(id);
    }

    @PUT
    @Path("/{id}")
    public void atualizarCliente(@PathParam("id") Long id, ClienteDTO clienteDTO) {
        clienteService.atualizarCliente(id, clienteDTO);
    }

    @DELETE
    @Path("/{id}")
    public void excluirCliente(@PathParam("id") Long id) {
        clienteService.excluirCliente(id);
    }
}
