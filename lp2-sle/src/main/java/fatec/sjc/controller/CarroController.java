package fatec.sjc.controller;

import fatec.sjc.dto.CarroDTO;
import fatec.sjc.entity.Carro;
import fatec.sjc.service.CarroService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
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
    public Response salvarCarro(CarroDTO carroDTO) {
        try {
            Carro savedCarro = carroService.salvarCarro(carroDTO);
            return Response.status(Response.Status.CREATED).entity(savedCarro).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error ao salvar carro: " + e.getMessage())
                    .build();
        }
    }

    @GET
    public Response listarCarros() {
        try {
            List<Carro> carros = carroService.listarCarros();
            return Response.ok(carros).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error ao listar carros: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        try {
            Carro carro = carroService.buscarPorId(id);
            return Response.ok(carro).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Carro não encontrado pelo id " + id)
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarCarro(@PathParam("id") Long id, CarroDTO carroDTO) {
        try {
            carroService.atualizarCarro(carroDTO);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error ao atualizar carro: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirCarro(@PathParam("id") Long id) {
        try {
            carroService.excluirCarro(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error ao deletar carro: " + e.getMessage())
                    .build();
        }
    }
}
