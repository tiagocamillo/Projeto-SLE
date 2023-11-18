package fatec.sjc.controller;

import fatec.sjc.entity.Carro;
import fatec.sjc.service.CarroService;
import jakarta.ws.rs.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import java.util.List;


@Path("/carros")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarroController {

    @Inject
    CarroService carroService;

    @GET
    public List<Carro> listarCarros() {
        return carroService.listarCarros();
    }

    @GET
    @Path("/{id}")
    public Carro buscarPorId(@PathParam("id") Long id) {
        return carroService.buscarPorId(id);
    }

    @POST
    public Carro salvarCarro(Carro carro) {
        return carroService.salvarCarro(carro);
    }

    @PUT
    @Path("/{id}")
    public void atualizarCarro(@PathParam("id") Long id, Carro carro) {
        Carro carroExistente = carroService.buscarPorId(id);
        if (carroExistente != null) {
            carro.setId(id);
            carroService.atualizarCarro(carro);
        } else {
            throw new NotFoundException("Carro não encontrado");
        }
    }

    @DELETE
    @Path("/{id}")
    public void excluirCarro(@PathParam("id") Long id) {
        carroService.excluirCarro(id);
    }
}