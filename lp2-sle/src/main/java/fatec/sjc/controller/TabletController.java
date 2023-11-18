package fatec.sjc.controller;

import fatec.sjc.entity.Tablet;
import fatec.sjc.service.TabletService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/tablets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TabletController {

    @Inject
    TabletService tabletService;

    @GET
    public List<Tablet> listarTablets() {
        return tabletService.listarTablets();
    }

    @GET
    @Path("/{id}")
    public Tablet buscarPorId(@PathParam("id") Long id) {
        return tabletService.buscarPorId(id);
    }

    @POST
    public Tablet salvarTablet(Tablet tablet) {
        return tabletService.salvarTablet(tablet);
    }

    @PUT
    @Path("/{id}")
    public void atualizarTablet(@PathParam("id") Long id, Tablet tablet) {
        Tablet tabletExistente = tabletService.buscarPorId(id);
        if (tabletExistente != null) {
            tablet.setId(id);
            tabletService.atualizarTablet(tablet);
        } else {
            throw new NotFoundException("Tablet não encontrado");
        }
    }

    @DELETE
    @Path("/{id}")
    public void excluirTablet(@PathParam("id") Long id) {
        tabletService.excluirTablet(id);
    }
}