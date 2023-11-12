package fatec.sjc.service;

import java.util.List;

import fatec.sjc.DTO.ClienteDTO;
import fatec.sjc.entity.Cliente;
import fatec.sjc.repository.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ClienteService {

    @Inject
    ClienteRepository clienteRepository;

    @Transactional
    public Cliente criarCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpfcnpj(clienteDTO.getCpfcnpj());

        clienteRepository.persist(cliente);
        return cliente;
    }

    @Transactional
    public Cliente atualizarCliente(Long id, ClienteDTO clienteAtualizado) {
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
