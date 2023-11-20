package fatec.sjc.controller;

import fatec.sjc.dto.LanceClienteDTO;
import fatec.sjc.entity.LanceCliente;
import fatec.sjc.service.LanceClienteService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
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
    public Response salvarLanceCliente(@PathParam("idProduto") Long idProduto,
                                       @PathParam("idCliente") Long idCliente,
                                       @PathParam("valor") double valor) {
        try {
            LanceCliente savedLanceCliente = lanceClienteService.salvarLanceCliente(idProduto, idCliente, valor);
            return Response.status(Response.Status.CREATED).entity(savedLanceCliente).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao salvar lance do cliente: " + e.getMessage())
                    .build();
        }
    }

    @GET
    public Response listarLancesClientes() {
        try {
            List<LanceCliente> lancesClientes = lanceClienteService.listarLancesClientes();
            return Response.ok(lancesClientes).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar lances do cliente: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        try {
            LanceCliente lanceCliente = lanceClienteService.buscarPorId(id);
            return Response.ok(lanceCliente).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Lance do cliente não encontrado com o ID " + id)
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarLanceCliente(@PathParam("id") Long id, LanceClienteDTO lanceClienteDTO) {
        try {
            lanceClienteService.atualizarLanceCliente(id, lanceClienteDTO);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar lance do cliente: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirLanceCliente(@PathParam("id") Long id) {
        try {
            lanceClienteService.excluirLanceCliente(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir lance do cliente: " + e.getMessage())
                    .build();
        }
    }
}
