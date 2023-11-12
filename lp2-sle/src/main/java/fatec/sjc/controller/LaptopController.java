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
@Path("/laptop")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LaptopController {

    @Inject
    LaptopService laptopService;

    @POST
    public Response criarLaptop(@Valid LaptopDTO laptopDTO) {
        try {
            LaptopDTO novoLaptop = laptopService.criarLaptop(laptopDTO);
            return Response.status(Response.Status.CREATED).entity(novoLaptop).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao criar o laptop.").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarLaptop(@PathParam("id") Long id, @Valid LaptopDTO laptopDTO) {
        try {
            LaptopDTO laptopAtualizado = laptopService.atualizarLaptop(id, laptopDTO);
            if (laptopAtualizado != null) {
                return Response.ok(laptopAtualizado).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Laptop não encontrado.").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao atualizar o laptop.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirLaptop(@PathParam("id") Long id) {
        try {
            laptopService.excluirLaptop(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao excluir o laptop.").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarLaptopPorId(@PathParam("id") Long id) {
        LaptopDTO laptop = laptopService.buscarLaptopPorId(id);
        if (laptop != null) {
            return Response.ok(laptop).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Laptop não encontrado.").build();
        }
    }

    @GET
    public Response listarLaptops() {
        try {
            List<LaptopDTO> laptops = laptopService.listarTodosOsLaptops();
            return Response.ok(laptops).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao listar os laptops.").build();
        }
    }
}