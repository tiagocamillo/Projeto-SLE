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

@Path("/tablet")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TabletController {

    @Inject
    TabletService tabletService;

    @POST
    public Response criarTablet(@Valid TabletDTO tabletDTO) {
        try {
            TabletDTO novoTablet = tabletService.criarTablet(tabletDTO);
            return Response.status(Response.Status.CREATED).entity(novoTablet).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao criar o tablet.").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarTablet(@PathParam("id") Long id, @Valid TabletDTO tabletDTO) {
        try {
            TabletDTO tabletAtualizado = tabletService.atualizarTablet(id, tabletDTO);
            if (tabletAtualizado != null) {
                return Response.ok(tabletAtualizado).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Tablet não encontrado.").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao atualizar o tablet.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirTablet(@PathParam("id") Long id) {
        try {
            tabletService.excluirTablet(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao excluir o tablet.").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarTabletPorId(@PathParam("id") Long id) {
        TabletDTO tablet = tabletService.buscarTabletPorId(id);
        if (tablet != null) {
            return Response.ok(tablet).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Tablet não encontrado.").build();
        }
    }

    @GET
    public Response listarTablets() {
        try {
            List<TabletDTO> tablets = tabletService.listarTodosOsTablets();
            return Response.ok(tablets).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao listar os tablets.").build();
        }
    }
}
