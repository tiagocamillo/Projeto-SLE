package fatec.sjc.controller;

import fatec.sjc.entity.Moto;
import fatec.sjc.service.MotoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/motos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MotoController {

    @Inject
    MotoService motoService;

    @GET
    public List<Moto> listarMotos() {
        return motoService.listarMotos();
    }

    @GET
    @Path("/{id}")
    public Moto buscarPorId(@PathParam("id") Long id) {
        return motoService.buscarPorId(id);
    }

    @POST
    public Moto salvarMoto(Moto moto) {
        return motoService.salvarMoto(moto);
    }

    @PUT
    @Path("/{id}")
    public void atualizarMoto(@PathParam("id") Long id, Moto moto) {
        Moto motoExistente = motoService.buscarPorId(id);
        if (motoExistente != null) {
            moto.setId(id);
            motoService.atualizarMoto(moto);
        } else {
            throw new NotFoundException("Moto não encontrada");
        }
    }

    @DELETE
    @Path("/{id}")
    public void excluirMoto(@PathParam("id") Long id) {
        motoService.excluirMoto(id);
    }
}