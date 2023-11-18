package fatec.sjc.controller;

import fatec.sjc.dto.OnibusDTO;
import fatec.sjc.entity.Onibus;
import fatec.sjc.service.OnibusService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/onibus")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OnibusController {

    private final OnibusService onibusService;

    @Inject
    public OnibusController(OnibusService onibusService) {
        this.onibusService = onibusService;
    }

    @POST
    public Onibus salvarOnibus(OnibusDTO onibusDTO) {
        return onibusService.salvarOnibus(onibusDTO);
    }

    @GET
    public List<Onibus> listarOnibus() {
        return onibusService.listarOnibus();
    }

    @GET
    @Path("/{id}")
    public Onibus buscarPorId(@PathParam("id") Long id) {
        return onibusService.buscarPorId(id);
    }

    @PUT
    @Path("/{id}")
    public void atualizarOnibus(@PathParam("id") Long id, OnibusDTO onibusDTO) {
        onibusService.atualizarOnibus(onibusDTO);
    }

    @DELETE
    @Path("/{id}")
    public void excluirOnibus(@PathParam("id") Long id) {
        onibusService.excluirOnibus(id);
    }
}
