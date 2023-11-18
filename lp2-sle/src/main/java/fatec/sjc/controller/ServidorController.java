package fatec.sjc.controller;

import fatec.sjc.dto.ServidorDTO;
import fatec.sjc.entity.Servidor;
import fatec.sjc.service.ServidorService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/servidores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServidorController {

    private final ServidorService servidorService;

    @Inject
    public ServidorController(ServidorService servidorService) {
        this.servidorService = servidorService;
    }

    @POST
    public Servidor salvarServidor(ServidorDTO servidorDTO) {
        return servidorService.salvarServidor(servidorDTO);
    }

    @GET
    public List<Servidor> listarServidores() {
        return servidorService.listarServidores();
    }

    @GET
    @Path("/{id}")
    public Servidor buscarPorId(@PathParam("id") Long id) {
        return servidorService.buscarPorId(id);
    }

    @PUT
    @Path("/{id}")
    public void atualizarServidor(@PathParam("id") Long id, ServidorDTO servidorDTO) {
        servidorService.atualizarServidor(servidorDTO);
    }

    @DELETE
    @Path("/{id}")
    public void excluirServidor(@PathParam("id") Long id) {
        servidorService.excluirServidor(id);
    }
}
