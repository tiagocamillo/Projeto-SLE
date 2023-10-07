package fatec.sjc.controller;

import fatec.sjc.entity.Leilao;
import fatec.sjc.service.LeilaoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/leilao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LeilaoController {

    @Inject
    LeilaoService leilaoService;

    @POST
    public Response criarLeilao(@Valid Leilao leilao) {
        try {
            Leilao novoLeilao = leilaoService.criarLeilao(leilao);
            return Response.status(Response.Status.CREATED).entity(novoLeilao).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Dados inválidos. Verifique os campos obrigatórios.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao criar o leilão.").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarLeilao(@PathParam("id") Long id, @Valid Leilao leilao) {
        try {
            Leilao leilaoAtualizado = leilaoService.atualizarLeilao(id, leilao);
            if (leilaoAtualizado != null) {
                return Response.ok(leilaoAtualizado).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Leilão não encontrado.").build();
            }
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Dados inválidos. Verifique os campos obrigatórios.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao atualizar o leilão.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirLeilao(@PathParam("id") Long id) {
        try {
            leilaoService.excluirLeilao(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao excluir o leilão.").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarLeilaoPorId(@PathParam("id") Long id) {
        Leilao leilao = leilaoService.buscarLeilaoPorId(id);
        if (leilao != null) {
            return Response.ok(leilao).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Leilão não encontrado.").build();
        }
    }

    @GET
    public Response listarTodosOsLeiloes() {
        try {
            List<Leilao> leiloes = leilaoService.listarTodosOsLeiloes();
            return Response.ok(leiloes).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao listar os leilões.").build();
        }
    }
}
