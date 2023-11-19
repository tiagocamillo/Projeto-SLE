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
    @Path("/produto/{idProduto}/cliente/{idCliente}/lance/{valor}")
    public LanceCliente salvarLanceCliente(@PathParam("idProduto") Long idProduto,
                                           @PathParam("idCliente") Long idCliente,
                                           @PathParam("valor") double valor) {
        return lanceClienteService.salvarLanceCliente(idProduto, idCliente, valor);
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
        lanceClienteService.atualizarLanceCliente(id, lanceClienteDTO);
    }

    @DELETE
    @Path("/{id}")
    public void excluirLanceCliente(@PathParam("id") Long id) {
        lanceClienteService.excluirLanceCliente(id);
    }
    
    
    
    
}
