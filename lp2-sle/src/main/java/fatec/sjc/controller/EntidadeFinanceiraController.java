package fatec.sjc.controller;

import fatec.sjc.dto.EntidadeFinanceiraDTO;
import fatec.sjc.entity.EntidadeFinanceira;
import fatec.sjc.service.EntidadeFinanceiraService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/entidades-financeiras")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EntidadeFinanceiraController {

    private final EntidadeFinanceiraService entidadeFinanceiraService;

    @Inject
    public EntidadeFinanceiraController(EntidadeFinanceiraService entidadeFinanceiraService) {
        this.entidadeFinanceiraService = entidadeFinanceiraService;
    }

    @POST
    public EntidadeFinanceira salvarEntidadeFinanceira(EntidadeFinanceiraDTO entidadeFinanceiraDTO) {
        return entidadeFinanceiraService.salvarEntidadeFinanceira(entidadeFinanceiraDTO);
    }

    @GET
    public List<EntidadeFinanceira> listarEntidadesFinanceiras() {
        return entidadeFinanceiraService.listarEntidadesFinanceiras();
    }

    @GET
    @Path("/{id}")
    public EntidadeFinanceira buscarPorId(@PathParam("id") Long id) {
        return entidadeFinanceiraService.buscarPorId(id);
    }

    @PUT
    @Path("/{id}")
    public void atualizarEntidadeFinanceira(@PathParam("id") Long id, EntidadeFinanceiraDTO entidadeFinanceiraDTO) {
        entidadeFinanceiraService.atualizarEntidadeFinanceira(entidadeFinanceiraDTO);
    }

    @DELETE
    @Path("/{id}")
    public void excluirEntidadeFinanceira(@PathParam("id") Long id) {
        entidadeFinanceiraService.excluirEntidadeFinanceira(id);
    }
}
