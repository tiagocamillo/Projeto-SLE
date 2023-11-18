package fatec.sjc.controller;

import fatec.sjc.dto.DispositivoDTO;
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

    private final DispositivoService dispositivoService;

    @Inject
    public DispositivoController(DispositivoService dispositivoService) {
        this.dispositivoService = dispositivoService;
    }

    @POST
    public Dispositivo salvarDispositivo(DispositivoDTO dispositivoDTO) {
        return dispositivoService.salvarDispositivo(dispositivoDTO);
    }

    @GET
    public List<Dispositivo> listarDispositivos() {
        return dispositivoService.listarDispositivos();
    }

    @GET
    @Path("/{id}")
    public Dispositivo buscarPorId(@PathParam("id") Long id) {
        return dispositivoService.buscarPorId(id);
    }

    @PUT
    @Path("/{id}")
    public void atualizarDispositivo(@PathParam("id") Long id, DispositivoDTO dispositivoDTO) {
        // Assuming you have an 'id' field in DispositivoDTO
        dispositivoDTO.setId(id);
        dispositivoService.atualizarDispositivo(dispositivoDTO);
    }

    @DELETE
    @Path("/{id}")
    public void excluirDispositivo(@PathParam("id") Long id) {
        dispositivoService.excluirDispositivo(id);
    }
}