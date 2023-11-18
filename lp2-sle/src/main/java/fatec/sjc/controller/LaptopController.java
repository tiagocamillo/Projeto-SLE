package fatec.sjc.controller;

import fatec.sjc.dto.LaptopDTO;
import fatec.sjc.entity.Laptop;
import fatec.sjc.service.LaptopService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
@Path("/laptops")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LaptopController {

    private final LaptopService laptopService;

    @Inject
    public LaptopController(LaptopService laptopService) {
        this.laptopService = laptopService;
    }

    @POST
    public Laptop salvarLaptop(LaptopDTO laptopDTO) {
        return laptopService.salvarLaptop(laptopDTO);
    }

    @GET
    public List<Laptop> listarLaptops() {
        return laptopService.listarLaptops();
    }

    @GET
    @Path("/{id}")
    public Laptop buscarPorId(@PathParam("id") Long id) {
        return laptopService.buscarPorId(id);
    }

    @PUT
    @Path("/{id}")
    public void atualizarLaptop(@PathParam("id") Long id, LaptopDTO laptopDTO) {

        laptopService.atualizarLaptop(laptopDTO);
    }

    @DELETE
    @Path("/{id}")
    public void excluirLaptop(@PathParam("id") Long id) {
        laptopService.excluirLaptop(id);
    }
}