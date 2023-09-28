package fatec.sjc.service;

import java.util.List;

import fatec.sjc.entity.Veiculo;
import fatec.sjc.repository.VeiculoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class VeiculoService {

	@Inject
	VeiculoRepository veiculoRepository;
	
    @Transactional    
    public Veiculo criarVeiculo(Veiculo veiculo) {
    	veiculoRepository.persist(veiculo);
        return veiculo;
	}
	
    @Transactional
    public Veiculo atualizarVeiculo(Long id, Veiculo veiculoAtualizado) {
    	Veiculo veiculoExistente = veiculoRepository.findById(id);
        if (veiculoExistente != null) {
        	veiculoExistente.setNome(veiculoAtualizado.getNome());
        	veiculoExistente.setDescricao(veiculoAtualizado.getDescricao());
        	veiculoExistente.setTipoProduto(veiculoAtualizado.getTipoProduto());
        }
        return veiculoExistente;
    }
    
    @Transactional
    public void excluirVeiculo(Long id) {
    	Veiculo veiculoExistente = veiculoRepository.findById(id);
        if (veiculoExistente != null) {
        	veiculoRepository.delete(veiculoExistente);
        }
    }
    
    public Veiculo buscarVeiculoPorId(Long id) {
        return veiculoRepository.findById(id);
    }

    public List<Veiculo> listarTodosOsVeiculos() {
        return veiculoRepository.listAll();
    }
    
    
    
    
    
    
    
    
	
}
