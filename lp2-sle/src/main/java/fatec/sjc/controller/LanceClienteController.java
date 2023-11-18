package fatec.sjc.controller;

import fatec.sjc.dto.LanceClienteDTO;
import fatec.sjc.entity.LanceCliente;
import fatec.sjc.service.LanceClienteService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/lances-clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LanceClienteController {

    private final LanceClienteService lanceClienteService;

    @Inject
    public LanceClienteController(LanceClienteService lanceClienteService) {
        this.lanceClienteService = lanceClienteService;
    }

    @POST
    public LanceCliente salvarLanceCliente(LanceClienteDTO lanceClienteDTO) {
        return lanceClienteService.salvarLanceCliente(lanceClienteDTO);
    }

    @GET
    public List<LanceCliente> listarLancesClientes() {
        return lanceClienteService.listarLancesClientes();
    }

    @GET
    @Path("/{id}")
    public LanceCliente buscarPorId(@PathParam("id") Long id) {
        return lanceClienteService.buscarPorId(id);
    }

    @PUT
    @Path("/{id}")
    public void atualizarLanceCliente(@PathParam("id") Long id, LanceClienteDTO lanceClienteDTO) {
        lanceClienteService.atualizarLanceCliente(lanceClienteDTO);
    }

    @DELETE
    @Path("/{id}")
    public void excluirLanceCliente(@PathParam("id") Long id) {
        lanceClienteService.excluirLanceCliente(id);
    }
}
