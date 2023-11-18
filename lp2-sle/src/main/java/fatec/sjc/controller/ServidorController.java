package fatec.sjc.controller;

import fatec.sjc.entity.Servidor;
import fatec.sjc.service.ServidorService;
import jakarta.ws.rs.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;


import java.util.List;


@Path("/servidores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServidorController {

    @Inject
    ServidorService servidorService;

    @GET
    public List<Servidor> listarServidores() {
        return servidorService.listarServidores();
    }

    @GET
    @Path("/{id}")
    public Servidor buscarPorId(@PathParam("id") Long id) {
        return servidorService.buscarPorId(id);
    }

    @POST
    public Servidor salvarServidor(Servidor servidor) {
        return servidorService.salvarServidor(servidor);
    }

    @PUT
    @Path("/{id}")
    public void atualizarServidor(@PathParam("id") Long id, Servidor servidor) {
        Servidor servidorExistente = servidorService.buscarPorId(id);
        if (servidorExistente != null) {
            servidor.setId(id);
            servidorService.atualizarServidor(servidor);
        } else {
            throw new NotFoundException("Servidor não encontrado");
        }
    }

    @DELETE
    @Path("/{id}")
    public void excluirServidor(@PathParam("id") Long id) {
        servidorService.excluirServidor(id);
    }
}