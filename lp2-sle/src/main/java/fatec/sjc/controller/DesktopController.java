package fatec.sjc.controller;

import fatec.sjc.entity.Desktop;
import fatec.sjc.service.DesktopService;
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

@Path("/desktop")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DesktopController {

    @Inject
    DesktopService desktopService;

    @POST
    public Response criarDesktop(@Valid Desktop desktop) {
        try {
            Desktop novoDesktop = desktopService.criarDesktop(desktop);
            return Response.status(Response.Status.CREATED).entity(novoDesktop).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Dados inválidos. Verifique os campos obrigatórios.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao criar o desktop.").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarDesktop(@PathParam("id") Long id, @Valid Desktop desktop) {
        try {
            Desktop desktopAtualizado = desktopService.atualizarDesktop(id, desktop);
            if (desktopAtualizado != null) {
                return Response.ok(desktopAtualizado).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Desktop não encontrado.").build();
            }
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Dados inválidos. Verifique os campos obrigatórios.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao atualizar o desktop.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirDesktop(@PathParam("id") Long id) {
        try {
            desktopService.deletarDesktop(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao excluir o desktop.").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarDesktopPorId(@PathParam("id") Long id) {
        Desktop desktop = desktopService.buscarDesktopPorId(id);
        if (desktop != null) {
            return Response.ok(desktop).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Desktop não encontrado.").build();
        }
    }

    @GET
    public Response listarDesktops() {
        try {
            List<Desktop> desktops = desktopService.listarDesktops();
            return Response.ok(desktops).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao listar os desktops.").build();
        }
    }
}
