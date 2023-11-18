package fatec.sjc.service;

import fatec.sjc.entity.Cliente;
import fatec.sjc.repository.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Inject
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public Cliente salvarCliente(Cliente cliente) {
        clienteRepository.persist(cliente);
        return cliente;
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.listAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Transactional
    public void atualizarCliente(Cliente cliente) {
        clienteRepository.persist(cliente);
    }

    @Transactional
    public void excluirCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}