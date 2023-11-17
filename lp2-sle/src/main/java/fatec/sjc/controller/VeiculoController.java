package fatec.sjc.controller;

import fatec.sjc.DTO.VeiculoDTO;
import fatec.sjc.entity.Veiculo;
import fatec.sjc.service.VeiculoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
@Path("/veiculos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VeiculoController {

    @Inject
    VeiculoService veiculoService;

    @POST
    public VeiculoDTO criarVeiculo(VeiculoDTO veiculoDTO) {
        return veiculoService.criarVeiculo(veiculoDTO);
    }

    @PUT
    @Path("/{id}")
    public VeiculoDTO atualizarVeiculo(@PathParam("id") Long id, VeiculoDTO veiculoDTO) {
        return veiculoService.atualizarVeiculo(id, veiculoDTO);
    }

    @DELETE
    @Path("/{id}")
    public void excluirVeiculo(@PathParam("id") Long id) {
        veiculoService.excluirVeiculo(id);
    }

    @GET
    @Path("/{id}")
    public VeiculoDTO buscarVeiculoPorId(@PathParam("id") Long id) {
        return veiculoService.buscarVeiculoPorId(id);
    }

    @GET
    public List<VeiculoDTO> listarTodosOsVeiculos() {
        return veiculoService.listarTodosOsVeiculos();
    }
}