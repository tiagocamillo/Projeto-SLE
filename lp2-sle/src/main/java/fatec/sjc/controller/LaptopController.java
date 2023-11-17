package fatec.sjc.controller;

import fatec.sjc.DTO.LaptopDTO;
import fatec.sjc.entity.Laptop;
import fatec.sjc.service.LaptopService;
import jakarta.validation.ConstraintViolationException;
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
import jakarta.ws.rs.core.Response;
import jakarta.validation.Valid;

import java.util.List;
@Path("/laptops")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LaptopController {

    @Inject
    LaptopService laptopService;

    @POST
    public LaptopDTO criarLaptop(LaptopDTO laptopDTO) {
        return laptopService.criarLaptop(laptopDTO);
    }

    @PUT
    @Path("/{id}")
    public LaptopDTO atualizarLaptop(@PathParam("id") Long id, LaptopDTO laptopDTO) {
        return laptopService.atualizarLaptop(id, laptopDTO);
    }

    @DELETE
    @Path("/{id}")
    public void excluirLaptop(@PathParam("id") Long id) {
        laptopService.excluirLaptop(id);
    }

    @GET
    @Path("/{id}")
    public LaptopDTO buscarLaptopPorId(@PathParam("id") Long id) {
        return laptopService.buscarLaptopPorId(id);
    }

    @GET
    public List<LaptopDTO> listarTodosOsLaptops() {
        return laptopService.listarTodosOsLaptops();
    }
}