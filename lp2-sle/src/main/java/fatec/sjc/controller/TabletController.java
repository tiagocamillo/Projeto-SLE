package fatec.sjc.controller;

import fatec.sjc.dto.TabletDTO;
import fatec.sjc.entity.Tablet;
import fatec.sjc.service.TabletService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
    public Response salvarTablet(TabletDTO tabletDTO) {
        try {
            Tablet savedTablet = tabletService.salvarTablet(tabletDTO);
            return Response.status(Response.Status.CREATED).entity(savedTablet).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao salvar tablet: " + e.getMessage())
                    .build();
        }
    }

    @GET
    public Response listarTablets() {
        try {
            List<Tablet> tablets = tabletService.listarTablets();
            return Response.ok(tablets).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar tablets: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        try {
            Tablet tablet = tabletService.buscarPorId(id);
            return Response.ok(tablet).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Tablet não encontrado com o ID " + id)
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarTablet(@PathParam("id") Long id, TabletDTO tabletDTO) {
        try {
            tabletService.atualizarTablet(tabletDTO);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar tablet: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirTablet(@PathParam("id") Long id) {
        try {
            tabletService.excluirTablet(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir tablet: " + e.getMessage())
                    .build();
        }
    }
}
