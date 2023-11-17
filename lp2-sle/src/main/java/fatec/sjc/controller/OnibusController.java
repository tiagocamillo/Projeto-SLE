package fatec.sjc.controller;

import fatec.sjc.DTO.OnibusDTO;
import fatec.sjc.entity.Onibus;
import fatec.sjc.service.OnibusService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.ws.rs.Path;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/onibus")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OnibusController {

    @Inject
    OnibusService onibusService;

    @POST
    public OnibusDTO criarOnibus(OnibusDTO onibusDTO) {
        return onibusService.criarOnibus(onibusDTO);
    }

    @PUT
    @Path("/{id}")
    public OnibusDTO atualizarOnibus(@PathParam("id") Long id, OnibusDTO onibusDTO) {
        return onibusService.atualizarOnibus(id, onibusDTO);
    }

    @DELETE
    @Path("/{id}")
    public void excluirOnibus(@PathParam("id") Long id) {
        onibusService.excluirOnibus(id);
    }

    @GET
    @Path("/{id}")
    public OnibusDTO buscarOnibusPorId(@PathParam("id") Long id) {
        return onibusService.buscarOnibusPorId(id);
    }

    @GET
    public List<OnibusDTO> listarTodosOsOnibus() {
        return onibusService.listarTodosOsOnibus();
    }
}