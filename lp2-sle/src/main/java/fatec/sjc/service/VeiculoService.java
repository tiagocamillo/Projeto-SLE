// VeiculoService.java
package fatec.sjc.service;

import fatec.sjc.entity.Veiculo;
import fatec.sjc.repository.VeiculoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    @Inject
    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    @Transactional
    public Veiculo salvarVeiculo(Veiculo veiculo) {
        veiculoRepository.persist(veiculo);
        return veiculo;
    }

    public List<Veiculo> listarVeiculos() {
        return veiculoRepository.listAll();
    }

    public Veiculo buscarPorId(Long id) {
        return veiculoRepository.findById(id);
    }

    @Transactional
    public void atualizarVeiculo(Veiculo veiculo) {
        veiculoRepository.persist(veiculo);
    }

    @Transactional
    public void excluirVeiculo(Long id) {
        veiculoRepository.deleteById(id);
    }
}