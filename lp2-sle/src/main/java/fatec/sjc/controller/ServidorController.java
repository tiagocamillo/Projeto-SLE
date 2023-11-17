package fatec.sjc.controller;

import fatec.sjc.service.ServidorService;
import jakarta.ws.rs.Path;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.validation.Valid;

import fatec.sjc.DTO.ServidorDTO;

import java.util.List;


@Path("/servidores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServidorController {

    @Inject
    ServidorService servidorService;

    @POST
    public ServidorDTO criarServidor(ServidorDTO servidorDTO) {
        return servidorService.criarServidor(servidorDTO);
    }

    @PUT
    @Path("/{id}")
    public ServidorDTO atualizarServidor(@PathParam("id") Long id, ServidorDTO servidorDTO) {
        return servidorService.atualizarServidor(id, servidorDTO);
    }

    @DELETE
    @Path("/{id}")
    public void excluirServidor(@PathParam("id") Long id) {
        servidorService.excluirServidor(id);
    }

    @GET
    @Path("/{id}")
    public ServidorDTO buscarServidorPorId(@PathParam("id") Long id) {
        return servidorService.buscarServidorPorId(id);
    }

    @GET
    public List<ServidorDTO> listarTodosOsServidores() {
        return servidorService.listarTodosOsServidores();
    }
}