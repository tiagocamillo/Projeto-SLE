package fatec.sjc.service;

import fatec.sjc.entity.LanceCliente;
import fatec.sjc.repository.LanceClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
@ApplicationScoped
public class LanceClienteService {

    private final LanceClienteRepository lanceClienteRepository;

    @Inject
    public LanceClienteService(LanceClienteRepository lanceClienteRepository) {
        this.lanceClienteRepository = lanceClienteRepository;
    }

    @Transactional
    public LanceCliente salvarLanceCliente(LanceCliente lanceCliente) {
        lanceClienteRepository.persist(lanceCliente);
        return lanceCliente;
    }

    public List<LanceCliente> listarLancesClientes() {
        return lanceClienteRepository.listAll();
    }

    public LanceCliente buscarPorId(Long id) {
        return lanceClienteRepository.findById(id);
    }

    @Transactional
    public void atualizarLanceCliente(LanceCliente lanceCliente) {
        lanceClienteRepository.persist(lanceCliente);
    }

    @Transactional
    public void excluirLanceCliente(Long id) {
        lanceClienteRepository.deleteById(id);
    }
}