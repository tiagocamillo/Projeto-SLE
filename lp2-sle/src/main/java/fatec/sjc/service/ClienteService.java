package fatec.sjc.service;

import java.util.List;

import fatec.sjc.dto.ClienteDTO;
import fatec.sjc.entity.Cliente;
import fatec.sjc.repository.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Inject
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.listAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Transactional
    public void atualizarCliente(Long id, ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findById(id);

        cliente.setNome(clienteDTO.getNome());
        cliente.setDetalhesContato(clienteDTO.getDetalhesContato());


        clienteRepository.persist(cliente);
    }
    
    @Transactional
    public Cliente salvarCliente(ClienteDTO clienteDTO) {
        Cliente novoCliente = new Cliente();
        novoCliente.setNome(clienteDTO.getNome());
        novoCliente.setDetalhesContato(clienteDTO.getDetalhesContato());

        clienteRepository.persist(novoCliente);
        return novoCliente;
    }
    @Transactional
    public void excluirCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
