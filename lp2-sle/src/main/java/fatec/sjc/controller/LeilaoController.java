package fatec.sjc.controller;

import fatec.sjc.entity.Leilao;
import fatec.sjc.service.LeilaoService;
import io.quarkus.hibernate.orm.panache.Panache;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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
    @Transactional
    public Response criarLeilao(Leilao leilao) {
        try {
            leilaoService.criarLeilao(leilao);
            return Response.status(Response.Status.CREATED).entity(leilao).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao criar o leilão.").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizarLeilao(@PathParam("id") Long id, Leilao leilaoAtualizado) {
        try {
            Leilao leilaoExistente = leilaoService.buscarLeilaoPorId(id);
            if (leilaoExistente == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            leilaoExistente.setDataInicio(leilaoAtualizado.getDataInicio());
            leilaoExistente.setStatus(leilaoAtualizado.getStatus());

            leilaoService.atualizarLeilao(id, leilaoExistente);

            return Response.ok(leilaoExistente).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao atualizar o leilão.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletarLeilao(@PathParam("id") Long id) {
        try {
            Leilao leilaoExistente = leilaoService.buscarLeilaoPorId(id);
            if (leilaoExistente == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            leilaoService.deletarLeilao(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao excluir o leilão.").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarLeilaoPorId(@PathParam("id") Long id) {
        try {
            Leilao leilao = leilaoService.buscarLeilaoPorId(id);
            if (leilao == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(leilao).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao buscar o leilão.").build();
        }
    }

    @GET
    public Response listarTodosLeiloes() {
        try {
            List<Leilao> leiloes = leilaoService.listarTodosLeiloes();
            return Response.ok(leiloes).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao listar os leilões.").build();
        }
    }
}