package fatec.sjc.controller;

import fatec.sjc.dto.LeilaoDTO;
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

    private final LeilaoService leilaoService;

    @Inject
    public LeilaoController(LeilaoService leilaoService) {
        this.leilaoService = leilaoService;
    }

    @POST
    public Leilao salvarLeilao(LeilaoDTO leilaoDTO) {
        return leilaoService.salvarLeilao(leilaoDTO);
    }

    @GET
    public List<Leilao> listarLeiloes() {
        return leilaoService.listarLeiloes();
    }

    @GET
    @Path("/{id}")
    public Leilao buscarPorId(@PathParam("id") Long id) {
        return leilaoService.buscarPorId(id);
    }

    @PUT
    @Path("/{id}")
    public void atualizarLeilao(@PathParam("id") Long id, LeilaoDTO leilaoDTO) {
        leilaoDTO.setId(id);
        leilaoService.atualizarLeilao(leilaoDTO);
    }

    @DELETE
    @Path("/{id}")
    public void excluirLeilao(@PathParam("id") Long id) {
        leilaoService.excluirLeilao(id);
    }
}