package fatec.sjc.controller;

import fatec.sjc.DTO.LeilaoDTO;
import fatec.sjc.entity.Leilao;
import fatec.sjc.service.LeilaoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Path("/leilao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LeilaoController {

    @Inject
    LeilaoService leilaoService;

    @POST
    public Response criarLeilao(@Valid LeilaoDTO leilaoDTO) {
        try {
            Leilao leilao = convertDTOToEntity(leilaoDTO);
            Leilao novoLeilao = leilaoService.criarLeilao(leilao);
            return Response.status(Response.Status.CREATED).entity(novoLeilao).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Dados inválidos. Verifique os campos obrigatórios.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao criar o leilão.").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarLeilao(@PathParam("id") Long id, @Valid LeilaoDTO leilaoDTO) {
        try {
            Leilao leilaoAtualizado = convertDTOToEntity(leilaoDTO);
            Leilao leilao = leilaoService.atualizarLeilao(id, leilaoAtualizado);
            if (leilao != null) {
                return Response.ok(leilao).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Leilão não encontrado.").build();
            }
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Dados inválidos. Verifique os campos obrigatórios.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao atualizar o leilão.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirLeilao(@PathParam("id") Long id) {
        try {
            leilaoService.excluirLeilao(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao excluir o leilão.").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarLeilaoPorId(@PathParam("id") Long id) {
        Leilao leilao = leilaoService.buscarLeilaoPorId(id);
        if (leilao != null) {
            LeilaoDTO leilaoDTO = convertEntityToDTO(leilao);
            return Response.ok(leilaoDTO).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Leilão não encontrado.").build();
        }
    }

    @GET
    public Response listarTodosOsLeiloes() {
        try {
            List<Leilao> leiloes = leilaoService.listarTodosOsLeiloes();
            List<LeilaoDTO> leiloesDTO = leiloes.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
            return Response.ok(leiloesDTO).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao listar os leilões.").build();
        }
    }

    private Leilao convertDTOToEntity(LeilaoDTO leilaoDTO) {
        Leilao leilao = new Leilao();
        leilao.setIdLeilao(leilaoDTO.getIdLeilao());
        leilao.setDataInicio(leilaoDTO.getDataInicio());
        leilao.setDataFim(leilaoDTO.getDataFim());
        leilao.setStatus(leilaoDTO.getStatus());
        leilao.setIdEntidadeFinanceiraAssociada(leilaoDTO.getIdEntidadeFinanceiraAssociada());
        return leilao;
    }

    private LeilaoDTO convertEntityToDTO(Leilao leilao) {
        LeilaoDTO leilaoDTO = new LeilaoDTO();
        leilaoDTO.setIdLeilao(leilao.getIdLeilao());
        leilaoDTO.setDataInicio((Date) leilao.getDataInicio());
        leilaoDTO.setDataFim((Date) leilao.getDataFim());
        leilaoDTO.setStatus(leilao.getStatus());
        leilaoDTO.setIdEntidadeFinanceiraAssociada(leilao.getIdEntidadeFinanceiraAssociada());
        return leilaoDTO;
    }
    
    @GET
    @Path("/listarOrdenadosPorData")
    public Response listarLeiloesOrdenadosPorData() {
        try {
            List<Leilao> leiloesOrdenados = leilaoService.listarLeiloesOrdenadosPorData();
            return Response.ok(leiloesOrdenados).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao listar os leilões.").build();
        }
    }
    
}