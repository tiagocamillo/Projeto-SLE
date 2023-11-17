package fatec.sjc.controller;

import fatec.sjc.DTO.CarroDTO;
import fatec.sjc.service.CarroService;
import jakarta.ws.rs.Path;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;


@Path("/carros")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarroController {

    @Inject
    CarroService carroService;

    @POST
    public CarroDTO criarCarro(CarroDTO carroDTO) {
        return carroService.criarCarro(carroDTO);
    }

    @PUT
    @Path("/{id}")
    public CarroDTO atualizarCarro(@PathParam("id") Long id, CarroDTO carroDTO) {
        return carroService.atualizarCarro(id, carroDTO);
    }

    @DELETE
    @Path("/{id}")
    public void excluirCarro(@PathParam("id") Long id) {
        carroService.excluirCarro(id);
    }

    @GET
    @Path("/{id}")
    public CarroDTO buscarCarroPorId(@PathParam("id") Long id) {
        return carroService.buscarCarroPorId(id);
    }

    @GET
    public List<CarroDTO> listarTodosOsCarros() {
        return carroService.listarTodosOsCarros();
    }
}