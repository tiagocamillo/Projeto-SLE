package fatec.sjc.controller;

import fatec.sjc.dto.VeiculoDTO;
import fatec.sjc.entity.Veiculo;
import fatec.sjc.service.VeiculoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/veiculos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VeiculoController {

    private final VeiculoService veiculoService;

    @Inject
    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @POST
    public Response salvarVeiculo(VeiculoDTO veiculoDTO) {
        try {
            Veiculo savedVeiculo = veiculoService.salvarVeiculo(veiculoDTO);
            return Response.status(Response.Status.CREATED).entity(savedVeiculo).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao salvar veículo: " + e.getMessage())
                    .build();
        }
    }

    @GET
    public Response listarVeiculos() {
        try {
            List<Veiculo> veiculos = veiculoService.listarVeiculos();
            return Response.ok(veiculos).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar veículos: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        try {
            Veiculo veiculo = veiculoService.buscarPorId(id);
            return Response.ok(veiculo).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Veículo não encontrado com o ID " + id)
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarVeiculo(@PathParam("id") Long id, VeiculoDTO veiculoDTO) {
        try {
            veiculoService.atualizarVeiculo(veiculoDTO);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar veículo: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirVeiculo(@PathParam("id") Long id) {
        try {
            veiculoService.excluirVeiculo(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir veículo: " + e.getMessage())
                    .build();
        }
    }
}
