package fatec.sjc.controller;

import fatec.sjc.DTO.DispositivoDTO;
import fatec.sjc.entity.Dispositivo;
import fatec.sjc.service.DispositivoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/dispositivos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DispositivoController {

    @Inject
    DispositivoService dispositivoService;

    @POST
    public DispositivoDTO criarDispositivo(DispositivoDTO dispositivoDTO) {
        return dispositivoService.criarDispositivo(dispositivoDTO);
    }

    @PUT
    @Path("/{id}")
    public DispositivoDTO atualizarDispositivo(@PathParam("id") Long id, DispositivoDTO dispositivoDTO) {
        return dispositivoService.atualizarDispositivo(id, dispositivoDTO);
    }

    @DELETE
    @Path("/{id}")
    public void excluirDispositivo(@PathParam("id") Long id) {
        dispositivoService.excluirDispositivo(id);
    }

    @GET
    @Path("/{id}")
    public DispositivoDTO buscarDispositivoPorId(@PathParam("id") Long id) {
        return dispositivoService.buscarDispositivoPorId(id);
    }

    @GET
    public List<DispositivoDTO> listarTodosOsDispositivos() {
        return dispositivoService.listarTodosOsDispositivos();
    }
}