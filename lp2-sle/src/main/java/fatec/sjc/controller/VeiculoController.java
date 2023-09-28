package fatec.sjc.controller;

import java.util.List;

import fatec.sjc.entity.Dispositivo;
import fatec.sjc.entity.Veiculo;
import fatec.sjc.service.VeiculoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/veiculo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VeiculoController {

	@Inject
	VeiculoService veiculoService;

    @POST
    public Veiculo criarVeiculo(Veiculo veiculo) {
        return veiculoService.criarVeiculo(veiculo);
    }

    @PUT
    @Path("/{id}")
    public Veiculo atualizarVeiculo(@PathParam("id") Long id, Veiculo veiculo) {
        return veiculoService.atualizarVeiculo(id, veiculo);
    }
    
    @DELETE
    @Path("/{id}")
    public void excluirVeiculo(@PathParam("id") Long id) {
    	veiculoService.excluirVeiculo(id);
    }

    @GET
    @Path("/{id}")
    public Veiculo buscarVeiculoPorId(@PathParam("id") Long id) {
        return veiculoService.buscarVeiculoPorId(id);
    }

    @GET
    public List<Veiculo> listarTodosOsVeiculos() {
        return veiculoService.listarTodosOsVeiculos();
    }
	
}
