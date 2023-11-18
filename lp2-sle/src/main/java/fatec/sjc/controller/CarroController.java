package fatec.sjc.controller;

import fatec.sjc.dto.CarroDTO;
import fatec.sjc.entity.Carro;
import fatec.sjc.service.CarroService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/carros")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarroController {

    private final CarroService carroService;

    @Inject
    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }

    @POST
    public Carro salvarCarro(CarroDTO carroDTO) {
        return carroService.salvarCarro(carroDTO);
    }

    @GET
    public List<Carro> listarCarros() {
        return carroService.listarCarros();
    }

    @GET
    @Path("/{id}")
    public Carro buscarPorId(@PathParam("id") Long id) {
        return carroService.buscarPorId(id);
    }

    @PUT
    @Path("/{id}")
    public void atualizarCarro(@PathParam("id") Long id, CarroDTO carroDTO) {
        carroService.atualizarCarro(carroDTO);
    }

    @DELETE
    @Path("/{id}")
    public void excluirCarro(@PathParam("id") Long id) {
        carroService.excluirCarro(id);
    }
}
