package fatec.sjc.controller;

import fatec.sjc.entity.Carro;
import fatec.sjc.service.CarroService;
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

import jakarta.ws.rs.core.*;
import jakarta.validation.Valid;

import java.util.List;


@Path("/carro")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarroController {

    @Inject
    CarroService carroService;

    @POST
    public Response criarCarro(@Valid Carro carro) {
        try {
            Carro novoCarro = carroService.criarCarro(carro);
            return Response.status(Response.Status.CREATED).entity(novoCarro).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Dados inválidos. Verifique os campos obrigatórios.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao criar o carro.").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarCarro(@PathParam("id") Long id, @Valid Carro carro) {
        try {
            Carro carroAtualizado = carroService.atualizarCarro(id, carro);
            if (carroAtualizado != null) {
                return Response.ok(carroAtualizado).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Carro não encontrado.").build();
            }
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Dados inválidos. Verifique os campos obrigatórios.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao atualizar o carro.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirCarro(@PathParam("id") Long id) {
        try {
            carroService.deletarCarro(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao excluir o carro.").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarCarroPorId(@PathParam("id") Long id) {
        Carro carro = carroService.buscarCarroPorId(id);
        if (carro != null) {
            return Response.ok(carro).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Carro não encontrado.").build();
        }
    }

    @GET
    public Response listarCarros() {
        try {
            List<Carro> carros = carroService.listarCarros();
            return Response.ok(carros).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao listar os carros.").build();
        }
    }
}

