package fatec.sjc.controller;

import fatec.sjc.DTO.MotoDTO;
import fatec.sjc.entity.Moto;
import fatec.sjc.service.MotoService;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/motos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MotoController {

    @Inject
    MotoService motoService;

    @POST
    public MotoDTO criarMoto(MotoDTO motoDTO) {
        return motoService.criarMoto(motoDTO);
    }

    @PUT
    @Path("/{id}")
    public MotoDTO atualizarMoto(@PathParam("id") Long id, MotoDTO motoDTO) {
        return motoService.atualizarMoto(id, motoDTO);
    }

    @DELETE
    @Path("/{id}")
    public void excluirMoto(@PathParam("id") Long id) {
        motoService.excluirMoto(id);
    }

    @GET
    @Path("/{id}")
    public MotoDTO buscarMotoPorId(@PathParam("id") Long id) {
        return motoService.buscarMotoPorId(id);
    }

    @GET
    public List<MotoDTO> listarTodasAsMotos() {
        return motoService.listarTodasAsMotos();
    }
}