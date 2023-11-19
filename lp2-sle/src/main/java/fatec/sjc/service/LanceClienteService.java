package fatec.sjc.service;

import java.time.LocalDateTime;
import java.util.List;

import fatec.sjc.dto.LanceClienteDTO;
import fatec.sjc.entity.Cliente;
import fatec.sjc.entity.LanceCliente;
import fatec.sjc.entity.Produto;
import fatec.sjc.repository.ClienteRepository;
import fatec.sjc.repository.LanceClienteRepository;
import fatec.sjc.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class LanceClienteService {

	private final LanceClienteRepository lanceClienteRepository;
    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;

    @Inject
    public LanceClienteService(LanceClienteRepository lanceClienteRepository, ProdutoRepository produtoRepository, ClienteRepository clienteRepository) {
        this.lanceClienteRepository = lanceClienteRepository;
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public LanceCliente salvarLanceCliente(LanceClienteDTO lanceClienteDTO) {
        LanceCliente lanceCliente = new LanceCliente();
        lanceCliente.setValor(lanceClienteDTO.getValor());
        lanceCliente.setDataHoraLance(LocalDateTime.now());

        Produto produto = produtoRepository.findById(lanceClienteDTO.getIdProduto());
        if (produto == null) {
            throw new RuntimeException("Produto não encontrado com o ID: " + lanceClienteDTO.getIdProduto());
        }
        lanceCliente.setProduto(produto);

        Cliente cliente = clienteRepository.findById(lanceClienteDTO.getIdCliente());
        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado com o ID: " + lanceClienteDTO.getIdCliente());
        }
        lanceCliente.setCliente(cliente);

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
    public void atualizarLanceCliente(Long id, LanceClienteDTO lanceClienteDTO) {
        LanceCliente lanceCliente = lanceClienteRepository.findById(id);
        if (lanceCliente == null) {
            throw new RuntimeException("Lance não encontrado com o ID: " + id);
        }

        lanceCliente.setValor(lanceClienteDTO.getValor());
        lanceCliente.setDataHoraLance(LocalDateTime.now());

        Produto produto = produtoRepository.findById(lanceClienteDTO.getIdProduto());
        if (produto == null) {
            throw new RuntimeException("Produto não encontrado com o ID: " + lanceClienteDTO.getIdProduto());
        }
        lanceCliente.setProduto(produto);

        Cliente cliente = clienteRepository.findById(lanceClienteDTO.getIdCliente());
        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado com o ID: " + lanceClienteDTO.getIdCliente());
        }
        lanceCliente.setCliente(cliente);

        lanceClienteRepository.persist(lanceCliente);
    }

    @Transactional
    public void excluirLanceCliente(Long id) {
        lanceClienteRepository.deleteById(id);
    }
}
