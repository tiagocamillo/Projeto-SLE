package fatec.sjc.controller;

import fatec.sjc.DTO.TabletDTO;
import fatec.sjc.entity.Tablet;
import fatec.sjc.service.TabletService;
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
import jakarta.ws.rs.core.Response;
import jakarta.validation.Valid;

import java.util.List;

@Path("/tablets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TabletController {

    @Inject
    TabletService tabletService;

    @POST
    public TabletDTO criarTablet(TabletDTO tabletDTO) {
        return tabletService.criarTablet(tabletDTO);
    }

    @PUT
    @Path("/{id}")
    public TabletDTO atualizarTablet(@PathParam("id") Long id, TabletDTO tabletDTO) {
        return tabletService.atualizarTablet(id, tabletDTO);
    }

    @DELETE
    @Path("/{id}")
    public void excluirTablet(@PathParam("id") Long id) {
        tabletService.excluirTablet(id);
    }

    @GET
    @Path("/{id}")
    public TabletDTO buscarTabletPorId(@PathParam("id") Long id) {
        return tabletService.buscarTabletPorId(id);
    }

    @GET
    public List<TabletDTO> listarTodosOsTablets() {
        return tabletService.listarTodosOsTablets();
    }
}