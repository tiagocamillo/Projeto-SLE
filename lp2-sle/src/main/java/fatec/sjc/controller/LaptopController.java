package fatec.sjc.controller;

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

    @Inject
    LaptopService laptopService;

    @GET
    public List<Laptop> listarLaptops() {
        return laptopService.listarLaptops();
    }

    @GET
    @Path("/{id}")
    public Laptop buscarPorId(@PathParam("id") Long id) {
        return laptopService.buscarPorId(id);
    }

    @POST
    public Laptop salvarLaptop(Laptop laptop) {
        return laptopService.salvarLaptop(laptop);
    }

    @PUT
    @Path("/{id}")
    public void atualizarLaptop(@PathParam("id") Long id, Laptop laptop) {
        Laptop laptopExistente = laptopService.buscarPorId(id);
        if (laptopExistente != null) {
            laptop.setId(id);
            laptopService.atualizarLaptop(laptop);
        } else {
            throw new NotFoundException("Laptop não encontrado");
        }
    }

    @DELETE
    @Path("/{id}")
    public void excluirLaptop(@PathParam("id") Long id) {
        laptopService.excluirLaptop(id);
    }
}