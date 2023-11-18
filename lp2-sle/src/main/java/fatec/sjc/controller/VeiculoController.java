package fatec.sjc.controller;

import fatec.sjc.dto.VeiculoDTO;
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

    private final VeiculoService veiculoService;

    @Inject
    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @POST
    public Veiculo salvarVeiculo(VeiculoDTO veiculoDTO) {
        return veiculoService.salvarVeiculo(veiculoDTO);
    }

    @GET
    public List<Veiculo> listarVeiculos() {
        return veiculoService.listarVeiculos();
    }

    @GET
    @Path("/{id}")
    public Veiculo buscarPorId(@PathParam("id") Long id) {
        return veiculoService.buscarPorId(id);
    }

    @PUT
    @Path("/{id}")
    public void atualizarVeiculo(@PathParam("id") Long id, VeiculoDTO veiculoDTO) {
        veiculoService.atualizarVeiculo(veiculoDTO);
    }

    @DELETE
    @Path("/{id}")
    public void excluirVeiculo(@PathParam("id") Long id) {
        veiculoService.excluirVeiculo(id);
    }
}
