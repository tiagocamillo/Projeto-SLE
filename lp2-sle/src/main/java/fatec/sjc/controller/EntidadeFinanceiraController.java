package fatec.sjc.controller;

import fatec.sjc.entity.EntidadeFinanceira;
import fatec.sjc.service.EntidadesFinanceiraService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/entidades-financeiras")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EntidadeFinanceiraController {

    @Inject
    EntidadesFinanceiraService entidadesFinanceiraService;

    @POST
    public EntidadeFinanceira criarEntidadesFinanceira(EntidadeFinanceira entidadeFinanceira) {
        return entidadesFinanceiraService.criarEntidadesFinanceira(entidadeFinanceira);
    }

    @PUT
    @Path("/{id}")
    public EntidadeFinanceira atualizarEntidadesFinanceira(@PathParam("id") Long id, EntidadeFinanceira entidadeFinanceira) {
        return entidadesFinanceiraService.atualizarEntidadeFinanceira(id, entidadeFinanceira);
    }

    @DELETE
    @Path("/{id}")
    public void excluirEntidadesFinanceira(@PathParam("id") Long id) {
        entidadesFinanceiraService.excluirEntidadeFinanceira(id);
    }

    @GET
    @Path("/{id}")
    public EntidadeFinanceira buscarEntidadesFinanceiraPorId(@PathParam("id") Long id) {
        return entidadesFinanceiraService.buscarEntidadeFinanceiraPorId(id);
    }

    @GET
    public List<EntidadeFinanceira> listarTodasAsEntidadesFinanceiras() {
        return entidadesFinanceiraService.listarTodasAsEntidadesFinanceiras();
    }
}
