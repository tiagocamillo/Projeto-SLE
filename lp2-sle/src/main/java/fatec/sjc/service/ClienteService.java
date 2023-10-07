package fatec.sjc.service;

import fatec.sjc.entity.Cliente;
import fatec.sjc.repository.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ClienteService {

    @Inject
    ClienteRepository clienteRepository;

    @Transactional
    public Cliente criarCliente(Cliente cliente) {
        clienteRepository.persist(cliente);
        return cliente;
    }

    @Transactional
    public Cliente atualizarCliente(Long id, Cliente clienteAtualizado) {
        Cliente clienteExistente = clienteRepository.findById(id);
        if (clienteExistente != null) {
            clienteExistente.setNome(clienteAtualizado.getNome());
            clienteExistente.setCpfcnpj(clienteAtualizado.getCpfcnpj());
        }
        return clienteExistente;
    }

    @Transactional
    public void excluirCliente(Long id) {
        Cliente clienteExistente = clienteRepository.findById(id);
        if (clienteExistente != null) {
            clienteRepository.delete(clienteExistente);
        }
    }

    public Cliente buscarClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    public List<Cliente> listarTodosOsClientes() {
        return clienteRepository.listAll();
    }
}
