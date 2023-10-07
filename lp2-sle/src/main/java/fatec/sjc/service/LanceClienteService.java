package fatec.sjc.service;

import java.util.List;

import fatec.sjc.entity.LanceCliente;
import fatec.sjc.repository.LanceClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class LanceClienteService {

    @Inject
    LanceClienteRepository lanceClienteRepository;

    @Transactional
    public LanceCliente criarLanceCliente(LanceCliente lanceCliente) {
        lanceClienteRepository.persist(lanceCliente);
        return lanceCliente;
    }

    @Transactional
    public LanceCliente atualizarLanceCliente(Long id, LanceCliente lanceClienteAtualizado) {
        LanceCliente lanceClienteExistente = lanceClienteRepository.findById(id);
        if (lanceClienteExistente != null) {
            // Atualize os campos necessários
        }
        return lanceClienteExistente;
    }

    @Transactional
    public void excluirLanceCliente(Long id) {
        LanceCliente lanceClienteExistente = lanceClienteRepository.findById(id);
        if (lanceClienteExistente != null) {
            lanceClienteRepository.delete(lanceClienteExistente);
        }
    }

    public LanceCliente buscarLanceClientePorId(Long id) {
        return lanceClienteRepository.findById(id);
    }

    public List<LanceCliente> listarTodosOsLancesClientes() {
        return lanceClienteRepository.listAll();
    }
}
