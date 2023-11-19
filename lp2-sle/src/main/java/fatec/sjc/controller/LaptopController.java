package fatec.sjc.controller;

import fatec.sjc.dto.LaptopDTO;
import fatec.sjc.entity.Laptop;
import fatec.sjc.service.LaptopService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
    public Response salvarLaptop(LaptopDTO laptopDTO) {
        try {
            Laptop savedLaptop = laptopService.salvarLaptop(laptopDTO);
            return Response.status(Response.Status.CREATED).entity(savedLaptop).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao salvar laptop: " + e.getMessage())
                    .build();
        }
    }

    @GET
    public Response listarLaptops() {
        try {
            List<Laptop> laptops = laptopService.listarLaptops();
            return Response.ok(laptops).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar laptops: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        try {
            Laptop laptop = laptopService.buscarPorId(id);
            return Response.ok(laptop).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Laptop não encontrado com o ID " + id)
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarLaptop(@PathParam("id") Long id, LaptopDTO laptopDTO) {
        try {
            laptopService.atualizarLaptop(laptopDTO);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar laptop: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirLaptop(@PathParam("id") Long id) {
        try {
            laptopService.excluirLaptop(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir laptop: " + e.getMessage())
                    .build();
        }
    }
}
