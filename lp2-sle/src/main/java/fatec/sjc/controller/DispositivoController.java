package fatec.sjc.controller;

import java.util.List;

import fatec.sjc.entity.Dispositivo;
import fatec.sjc.service.DispositivoService;
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

@Path("/dispositivo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DispositivoController {

	@Inject
	DispositivoService dispositivoService;

    @POST
    public Dispositivo criarDispositivo(Dispositivo dispositivo) {
        return dispositivoService.criarDispositivo(dispositivo);
    }

    @PUT
    @Path("/{id}")
    public Dispositivo atualizarDispositivo(@PathParam("id") Long id, Dispositivo dispositivo) {
        return dispositivoService.atualizarDispositivo(id, dispositivo);
    }

    @DELETE
    @Path("/{id}")
    public void excluirDispositivo(@PathParam("id") Long id) {
    	dispositivoService.excluirDispositivo(id);
    }

    @GET
    @Path("/{id}")
    public Dispositivo buscarDispositivoPorId(@PathParam("id") Long id) {
        return dispositivoService.buscarDispositivoPorId(id);
    }

    @GET
    public List<Dispositivo> listarTodosOsDispositivos() {
        return dispositivoService.listarTodosOsDispositivos();
    }
	
}
