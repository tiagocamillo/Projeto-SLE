package fatec.sjc.controller;

import fatec.sjc.dto.MotoDTO;
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

    private final MotoService motoService;

    @Inject
    public MotoController(MotoService motoService) {
        this.motoService = motoService;
    }

    @POST
    public Moto salvarMoto(MotoDTO motoDTO) {
        return motoService.salvarMoto(motoDTO);
    }

    @GET
    public List<Moto> listarMotos() {
        return motoService.listarMotos();
    }

    @GET
    @Path("/{id}")
    public Moto buscarPorId(@PathParam("id") Long id) {
        return motoService.buscarPorId(id);
    }

    @PUT
    @Path("/{id}")
    public void atualizarMoto(@PathParam("id") Long id, MotoDTO motoDTO) {
        motoService.atualizarMoto(motoDTO);
    }

    @DELETE
    @Path("/{id}")
    public void excluirMoto(@PathParam("id") Long id) {
        motoService.excluirMoto(id);
    }
}