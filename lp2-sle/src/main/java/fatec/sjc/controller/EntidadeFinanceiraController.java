package fatec.sjc.controller;

import fatec.sjc.entity.EntidadeFinanceira;
import fatec.sjc.service.EntidadeFinanceiraService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/entidadesfinanceiras")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EntidadeFinanceiraController {

    @Inject
    EntidadeFinanceiraService entidadeFinanceiraService;

    @GET
    public List<EntidadeFinanceira> listarEntidadesFinanceiras() {
        return entidadeFinanceiraService.listarEntidadesFinanceiras();
    }

    @GET
    @Path("/{id}")
    public EntidadeFinanceira buscarPorId(@PathParam("id") Long id) {
        return entidadeFinanceiraService.buscarPorId(id);
    }

    @POST
    public EntidadeFinanceira salvarEntidadeFinanceira(EntidadeFinanceira entidadeFinanceira) {
        return entidadeFinanceiraService.salvarEntidadeFinanceira(entidadeFinanceira);
    }

    @PUT
    @Path("/{id}")
    public void atualizarEntidadeFinanceira(@PathParam("id") Long id, EntidadeFinanceira entidadeFinanceira) {
        EntidadeFinanceira entidadeFinanceiraExistente = entidadeFinanceiraService.buscarPorId(id);
        if (entidadeFinanceiraExistente != null) {
            entidadeFinanceira.setId(id);
            entidadeFinanceiraService.atualizarEntidadeFinanceira(entidadeFinanceira);
        } else {
            throw new NotFoundException("Entidade Financeira não encontrada");
        }
    }

    @DELETE
    @Path("/{id}")
    public void excluirEntidadeFinanceira(@PathParam("id") Long id) {
        entidadeFinanceiraService.excluirEntidadeFinanceira(id);
    }
}