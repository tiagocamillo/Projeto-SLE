package fatec.sjc.controller;

import fatec.sjc.entity.Veiculo;
import fatec.sjc.service.VeiculoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/veiculos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VeiculoController {

    @Inject
    VeiculoService veiculoService;

    @GET
    public List<Veiculo> listarVeiculos() {
        return veiculoService.listarVeiculos();
    }

    @GET
    @Path("/{id}")
    public Veiculo buscarPorId(@PathParam("id") Long id) {
        return veiculoService.buscarPorId(id);
    }

    @POST
    public Veiculo salvarVeiculo(Veiculo veiculo) {
        return veiculoService.salvarVeiculo(veiculo);
    }

    @PUT
    @Path("/{id}")
    public void atualizarVeiculo(@PathParam("id") Long id, Veiculo veiculo) {
        Veiculo veiculoExistente = veiculoService.buscarPorId(id);
        if (veiculoExistente != null) {
            veiculo.setId(id);
            veiculoService.atualizarVeiculo(veiculo);
        } else {
            throw new NotFoundException("Veículo não encontrado");
        }
    }

    @DELETE
    @Path("/{id}")
    public void excluirVeiculo(@PathParam("id") Long id) {
        veiculoService.excluirVeiculo(id);
    }
}
