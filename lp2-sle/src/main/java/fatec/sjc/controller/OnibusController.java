package fatec.sjc.controller;

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

    @Inject
    OnibusService onibusService;

    @GET
    public List<Onibus> listarOnibus() {
        return onibusService.listarOnibus();
    }

    @GET
    @Path("/{id}")
    public Onibus buscarPorId(@PathParam("id") Long id) {
        return onibusService.buscarPorId(id);
    }

    @POST
    public Onibus salvarOnibus(Onibus onibus) {
        return onibusService.salvarOnibus(onibus);
    }

    @PUT
    @Path("/{id}")
    public void atualizarOnibus(@PathParam("id") Long id, Onibus onibus) {
        Onibus onibusExistente = onibusService.buscarPorId(id);
        if (onibusExistente != null) {
            onibus.setId(id);
            onibusService.atualizarOnibus(onibus);
        } else {
            throw new NotFoundException("Ônibus não encontrado");
        }
    }

    @DELETE
    @Path("/{id}")
    public void excluirOnibus(@PathParam("id") Long id) {
        onibusService.excluirOnibus(id);
    }
}