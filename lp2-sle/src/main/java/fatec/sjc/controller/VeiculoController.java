package fatec.sjc.controller;

import fatec.sjc.entity.Veiculo;
import fatec.sjc.service.VeiculoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/veiculo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VeiculoController {

    @Inject
    VeiculoService veiculoService;

    @POST
    public Response criarVeiculo(@Valid Veiculo veiculo) {
        try {
            Veiculo novoVeiculo = veiculoService.criarVeiculo(veiculo);
            return Response.status(Response.Status.CREATED).entity(novoVeiculo).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Dados inválidos. Verifique os campos obrigatórios.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao criar o veículo.").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarVeiculo(@PathParam("id") Long id, @Valid Veiculo veiculo) {
        try {
            Veiculo veiculoAtualizado = veiculoService.atualizarVeiculo(id, veiculo);
            if (veiculoAtualizado != null) {
                return Response.ok(veiculoAtualizado).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Veículo não encontrado.").build();
            }
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Dados inválidos. Verifique os campos obrigatórios.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao atualizar o veículo.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirVeiculo(@PathParam("id") Long id) {
        try {
            veiculoService.excluirVeiculo(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao excluir o veículo.").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarVeiculoPorId(@PathParam("id") Long id) {
        Veiculo veiculo = veiculoService.buscarVeiculoPorId(id);
        if (veiculo != null) {
            return Response.ok(veiculo).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Veículo não encontrado.").build();
        }
    }

    @GET
    public Response listarTodosOsVeiculos() {
        try {
            List<Veiculo> veiculos = veiculoService.listarTodosOsVeiculos();
            return Response.ok(veiculos).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao listar os veículos.").build();
        }
    }
}
