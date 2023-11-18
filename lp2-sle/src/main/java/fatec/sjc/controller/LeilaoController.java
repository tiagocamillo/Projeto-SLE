package fatec.sjc.controller;

import fatec.sjc.entity.Leilao;
import fatec.sjc.service.LeilaoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
@Path("/leiloes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LeilaoController {

    @Inject
    LeilaoService leilaoService;

    @GET
    public List<Leilao> listarLeiloes() {
        return leilaoService.listarLeiloes();
    }

    @GET
    @Path("/{id}")
    public Leilao buscarPorId(@PathParam("id") Long id) {
        return leilaoService.buscarPorId(id);
    }

    @POST
    public Leilao salvarLeilao(Leilao leilao) {
        return leilaoService.salvarLeilao(leilao);
    }

    @PUT
    @Path("/{id}")
    public void atualizarLeilao(@PathParam("id") Long id, Leilao leilao) {
        Leilao leilaoExistente = leilaoService.buscarPorId(id);
        if (leilaoExistente != null) {
            leilao.setId(id);
            leilaoService.atualizarLeilao(leilao);
        } else {
            throw new NotFoundException("Leilão não encontrado");
        }
    }

    @DELETE
    @Path("/{id}")
    public void excluirLeilao(@PathParam("id") Long id) {
        leilaoService.excluirLeilao(id);
    }
}
