package fatec.sjc.controller;

import fatec.sjc.entity.Scanner;
import fatec.sjc.service.ScannerService;
import jakarta.validation.ConstraintViolationException;
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

import java.util.List;

@Path("/scanner")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ScannerController {

    @Inject
    ScannerService scannerService;

    @POST
    public Response criarScanner(@Valid Scanner scanner) {
        try {
            Scanner novoScanner = scannerService.criarScanner(scanner);
            return Response.status(Response.Status.CREATED).entity(novoScanner).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Dados inválidos. Verifique os campos obrigatórios.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao criar o scanner.").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarScanner(@PathParam("id") Long id, @Valid Scanner scanner) {
        try {
            Scanner scannerAtualizado = scannerService.atualizarScanner(id, scanner);
            if (scannerAtualizado != null) {
                return Response.ok(scannerAtualizado).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Scanner não encontrado.").build();
            }
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Dados inválidos. Verifique os campos obrigatórios.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao atualizar o scanner.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirScanner(@PathParam("id") Long id) {
        try {
            scannerService.deletarScanner(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao excluir o scanner.").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarScannerPorId(@PathParam("id") Long id) {
        Scanner scanner = scannerService.buscarScannerPorId(id);
        if (scanner != null) {
            return Response.ok(scanner).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Scanner não encontrado.").build();
        }
    }

    @GET
    public Response listarScanners() {
        try {
            List<Scanner> scanners = scannerService.listarScanners();
            return Response.ok(scanners).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao listar os scanners.").build();
        }
    }
}
