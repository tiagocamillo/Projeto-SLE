package fatec.sjc.controller;

import fatec.sjc.DTO.ItemLeilaoDTO;
import fatec.sjc.entity.ItemLeilao;
import fatec.sjc.service.ItemLeilaoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;  // Add this import statement

import java.util.List;




@Path("/itens-leilao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemLeilaoController {

    @Inject
    ItemLeilaoService itemLeilaoService;

    @POST
    public Response criarItemLeilao(@Valid ItemLeilaoDTO itemLeilaoDTO) {
        try {
            ItemLeilao itemLeilao = itemLeilaoService.criarItemLeilao(itemLeilaoDTO);
            return Response.status(Response.Status.CREATED)
                    .location(URI.create("/itens-leilao/" + itemLeilao.getLeilao()))
                    .entity(itemLeilao)
                    .build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }
    @PUT
    @Path("/{id}")
    public Response atualizarItemLeilao(
            @PathParam("id") Long id,
            @Valid ItemLeilaoDTO itemLeilaoDTO) {
        try {
            ItemLeilao itemLeilao = itemLeilaoService.atualizarItemLeilao(id, itemLeilaoDTO);
            if (itemLeilao != null) {
                return Response.ok(itemLeilao).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirItemLeilao(@PathParam("id") Long id) {
        boolean sucesso = itemLeilaoService.excluirItemLeilao(id);
        if (sucesso) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarItemLeilaoPorId(@PathParam("id") Long id) {
        ItemLeilao itemLeilao = itemLeilaoService.buscarItemLeilaoPorId(id);
        if (itemLeilao != null) {
            return Response.ok(itemLeilao).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public List<ItemLeilao> listarTodosOsItensLeilao() {
        return itemLeilaoService.listarTodosOsItensLeilao();
    }
}
