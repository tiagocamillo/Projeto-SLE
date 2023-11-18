package fatec.sjc.controller;

import fatec.sjc.entity.Dispositivo;
import fatec.sjc.service.DispositivoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/dispositivos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DispositivoController {

    @Inject
    DispositivoService dispositivoService;

    @GET
    public List<Dispositivo> listarDispositivos() {
        return dispositivoService.listarDispositivos();
    }

    @GET
    @Path("/{id}")
    public Dispositivo buscarPorId(@PathParam("id") Long id) {
        return dispositivoService.buscarPorId(id);
    }

    @POST
    public Dispositivo salvarDispositivo(Dispositivo dispositivo) {
        return dispositivoService.salvarDispositivo(dispositivo);
    }

    @PUT
    @Path("/{id}")
    public void atualizarDispositivo(@PathParam("id") Long id, Dispositivo dispositivo) {
        Dispositivo dispositivoExistente = dispositivoService.buscarPorId(id);
        if (dispositivoExistente != null) {
            dispositivo.setId(id);
            dispositivoService.atualizarDispositivo(dispositivo);
        } else {
            throw new NotFoundException("Dispositivo não encontrado");
        }
    }

    @DELETE
    @Path("/{id}")
    public void excluirDispositivo(@PathParam("id") Long id) {
        dispositivoService.excluirDispositivo(id);
    }
}