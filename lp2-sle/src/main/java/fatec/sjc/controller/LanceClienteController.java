package fatec.sjc.controller;

import fatec.sjc.DTO.LanceClienteDTO;
import fatec.sjc.entity.LanceCliente;
import fatec.sjc.service.LanceClienteService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/lancecliente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LanceClienteController {

    @Inject
    LanceClienteService lanceClienteService;

    @POST
    public Response criarLanceCliente(@Valid LanceClienteDTO lanceClienteDTO) {
        try {
            LanceCliente lanceCliente = lanceClienteService.criarLanceCliente(lanceClienteDTO);
            LanceClienteDTO novoLanceClienteDTO = convertEntityToDTO(lanceCliente);
            return Response.status(Response.Status.CREATED).entity(novoLanceClienteDTO).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Dados inválidos. Verifique os campos obrigatórios.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao criar o lance do cliente.").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarLanceCliente(@PathParam("id") Long id, @Valid LanceClienteDTO lanceClienteDTO) {
        try {
            LanceCliente lanceClienteAtualizado = lanceClienteService.atualizarLanceCliente(id, lanceClienteDTO);
            if (lanceClienteAtualizado != null) {
                LanceClienteDTO atualizadoDTO = convertEntityToDTO(lanceClienteAtualizado);
                return Response.ok(atualizadoDTO).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Lance do cliente não encontrado.").build();
            }
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Dados inválidos. Verifique os campos obrigatórios.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao atualizar o lance do cliente.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirLanceCliente(@PathParam("id") Long id) {
        try {
            lanceClienteService.excluirLanceCliente(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao excluir o lance do cliente.").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarLanceClientePorId(@PathParam("id") Long id) {
        LanceCliente lanceCliente = lanceClienteService.buscarLanceClientePorId(id);
        if (lanceCliente != null) {
            LanceClienteDTO lanceClienteDTO = convertEntityToDTO(lanceCliente);
            return Response.ok(lanceClienteDTO).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Lance do cliente não encontrado.").build();
        }
    }

    @GET
    public Response listarTodosOsLancesClientes() {
        try {
            List<LanceCliente> lancesClientes = lanceClienteService.listarTodosOsLancesClientes();
            List<LanceClienteDTO> lancesDTO = lancesClientes.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
            return Response.ok(lancesDTO).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao listar os lances dos clientes.").build();
        }
    }

    private LanceClienteDTO convertEntityToDTO(LanceCliente lanceCliente) {
        LanceClienteDTO lanceClienteDTO = new LanceClienteDTO();
        lanceClienteDTO.setIdCliente(lanceCliente.getCliente().getId());
        lanceClienteDTO.setIdLeilao(lanceCliente.getLeilao().getIdLeilao());
        lanceClienteDTO.setValorLance(lanceCliente.getValorLance());
        return lanceClienteDTO;
    }
}