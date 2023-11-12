package fatec.sjc.controller;

import fatec.sjc.DTO.ItemLeilaoDTO;
import fatec.sjc.entity.ItemLeilao;
import fatec.sjc.service.ItemLeilaoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/itemleilao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemLeilaoController {

    @Inject
    ItemLeilaoService itemLeilaoService;

    @POST
    public Response criarItemLeilao(@Valid ItemLeilaoDTO itemLeilaoDTO) {
        try {
            ItemLeilao novoItemLeilao = itemLeilaoService.criarItemLeilao(itemLeilaoDTO);
            return Response.status(Response.Status.CREATED).entity(novoItemLeilao).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Dados inválidos. Verifique os campos obrigatórios.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao criar o item do leilão.").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarItemLeilao(@PathParam("id") Long id, @Valid ItemLeilaoDTO itemLeilaoDTO) {
        try {
            ItemLeilao itemLeilaoAtualizado = itemLeilaoService.atualizarItemLeilao(id, itemLeilaoDTO);
            if (itemLeilaoAtualizado != null) {
                return Response.ok(itemLeilaoAtualizado).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Item do leilão não encontrado.").build();
            }
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Dados inválidos. Verifique os campos obrigatórios.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao atualizar o item do leilão.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirItemLeilao(@PathParam("id") Long id) {
        try {
            itemLeilaoService.excluirItemLeilao(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao excluir o item do leilão.").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarItemLeilaoPorId(@PathParam("id") Long id) {
        ItemLeilao itemLeilao = itemLeilaoService.buscarItemLeilaoPorId(id);
        if (itemLeilao != null) {
            return Response.ok(itemLeilao).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Item do leilão não encontrado.").build();
        }
    }

    @GET
    public Response listarTodosOsItensLeilao() {
        try {
            List<ItemLeilao> itensLeilao = itemLeilaoService.listarTodosOsItensLeilao();
            return Response.ok(itensLeilao).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao listar os itens do leilão.").build();
        }
    }
}
