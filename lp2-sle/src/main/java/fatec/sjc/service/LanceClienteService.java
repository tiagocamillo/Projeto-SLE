package fatec.sjc.service;

import fatec.sjc.DTO.LanceClienteDTO;
import fatec.sjc.entity.Cliente;
import fatec.sjc.entity.LanceCliente;
import fatec.sjc.entity.Leilao;
import fatec.sjc.repository.ClienteRepository;
import fatec.sjc.repository.LanceClienteRepository;
import fatec.sjc.repository.LeilaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class LanceClienteService {

    @Inject
    LanceClienteRepository lanceClienteRepository;

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    LeilaoRepository leilaoRepository;

    @Transactional
    public LanceCliente criarLanceCliente(LanceClienteDTO lanceClienteDTO) {
        LanceCliente lanceCliente = convertDTOToEntity(lanceClienteDTO);
        lanceClienteRepository.persist(lanceCliente);
        return lanceCliente;
    }

    @Transactional
    public LanceCliente atualizarLanceCliente(Long id, LanceClienteDTO lanceClienteDTO) {
        LanceCliente lanceClienteExistente = lanceClienteRepository.findById(id);
        if (lanceClienteExistente != null) {
            LanceCliente lanceClienteAtualizado = convertDTOToEntity(lanceClienteDTO);
            lanceClienteExistente.setCliente(lanceClienteAtualizado.getCliente());
            lanceClienteExistente.setLeilao(lanceClienteAtualizado.getLeilao());
            lanceClienteExistente.setValorLance(lanceClienteAtualizado.getValorLance());
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

    private LanceCliente convertDTOToEntity(LanceClienteDTO lanceClienteDTO) {
        LanceCliente lanceCliente = new LanceCliente();

        Cliente cliente = clienteRepository.findById(lanceClienteDTO.getIdCliente());
        lanceCliente.setCliente(cliente);

        Leilao leilao = leilaoRepository.findById(lanceClienteDTO.getIdLeilao());
        lanceCliente.setLeilao(leilao);

        lanceCliente.setValorLance(lanceClienteDTO.getValorLance());

        return lanceCliente;
    }
}
