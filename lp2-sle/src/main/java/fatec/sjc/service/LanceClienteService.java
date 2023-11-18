package fatec.sjc.service;

import fatec.sjc.dto.LanceClienteDTO;
import fatec.sjc.entity.Cliente;
import fatec.sjc.entity.LanceCliente;
import fatec.sjc.entity.Produto;
import fatec.sjc.repository.LanceClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class LanceClienteService {

    private final LanceClienteRepository lanceClienteRepository;

    @Inject
    public LanceClienteService(LanceClienteRepository lanceClienteRepository) {
        this.lanceClienteRepository = lanceClienteRepository;
    }

    @Transactional
    public LanceCliente salvarLanceCliente(LanceClienteDTO lanceClienteDTO) {
        LanceCliente lanceCliente = new LanceCliente();
        lanceCliente.setValor(lanceClienteDTO.getValor());
        lanceCliente.setProduto(lanceClienteDTO.getProduto());
        lanceCliente.setCliente(lanceClienteDTO.getCliente());
        lanceCliente.setDataHoraLance(LocalDateTime.now());  // Assuming you want to set the current date and time
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
    public void atualizarLanceCliente(LanceClienteDTO lanceClienteDTO) {
        LanceCliente lanceCliente = new LanceCliente();
        lanceCliente.setValor(lanceClienteDTO.getValor());
        lanceCliente.setProduto(lanceClienteDTO.getProduto());
        lanceCliente.setCliente(lanceClienteDTO.getCliente());
        lanceCliente.setDataHoraLance(LocalDateTime.now());
        lanceClienteRepository.persist(lanceCliente);
    }

    @Transactional
    public void excluirLanceCliente(Long id) {
        lanceClienteRepository.deleteById(id);
    }
}
