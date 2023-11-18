package fatec.sjc.controller;

import fatec.sjc.dto.TabletDTO;
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

    private final TabletService tabletService;

    @Inject
    public TabletController(TabletService tabletService) {
        this.tabletService = tabletService;
    }

    @POST
    public Tablet salvarTablet(TabletDTO tabletDTO) {
        return tabletService.salvarTablet(tabletDTO);
    }

    @GET
    public List<Tablet> listarTablets() {
        return tabletService.listarTablets();
    }

    @GET
    @Path("/{id}")
    public Tablet buscarPorId(@PathParam("id") Long id) {
        return tabletService.buscarPorId(id);
    }

    @PUT
    @Path("/{id}")
    public void atualizarTablet(@PathParam("id") Long id, TabletDTO tabletDTO) {
        tabletService.atualizarTablet(tabletDTO);
    }

    @DELETE
    @Path("/{id}")
    public void excluirTablet(@PathParam("id") Long id) {
        tabletService.excluirTablet(id);
    }
}
