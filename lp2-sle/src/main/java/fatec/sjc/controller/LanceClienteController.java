package fatec.sjc.controller;

import fatec.sjc.entity.LanceCliente;
import fatec.sjc.service.LanceClienteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/lancesclientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LanceClienteController {

    @Inject
    LanceClienteService lanceClienteService;

    @GET
    public List<LanceCliente> listarLancesClientes() {
        return lanceClienteService.listarLancesClientes();
    }

    @GET
    @Path("/{id}")
    public LanceCliente buscarPorId(@PathParam("id") Long id) {
        return lanceClienteService.buscarPorId(id);
    }

    @POST
    public LanceCliente salvarLanceCliente(LanceCliente lanceCliente) {
        return lanceClienteService.salvarLanceCliente(lanceCliente);
    }

    @PUT
    @Path("/{id}")
    public void atualizarLanceCliente(@PathParam("id") Long id, LanceCliente lanceCliente) {
        LanceCliente lanceClienteExistente = lanceClienteService.buscarPorId(id);
        if (lanceClienteExistente != null) {
            lanceCliente.setId(id);
            lanceClienteService.atualizarLanceCliente(lanceCliente);
        } else {
            throw new NotFoundException("Lance do Cliente não encontrado");
        }
    }

    @DELETE
    @Path("/{id}")
    public void excluirLanceCliente(@PathParam("id") Long id) {
        lanceClienteService.excluirLanceCliente(id);
    }
}