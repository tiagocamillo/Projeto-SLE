package fatec.sjc.service;

import fatec.sjc.dto.VeiculoDTO;
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
    public Veiculo salvarVeiculo(VeiculoDTO veiculoDTO) {
        Veiculo veiculo = new Veiculo();
        atualizarVeiculoFromDTO(veiculo, veiculoDTO);
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
    public void atualizarVeiculo(VeiculoDTO veiculoDTO) {
        Veiculo veiculo = veiculoRepository.findById(veiculoDTO.getId());
        if (veiculo != null) {
            atualizarVeiculoFromDTO(veiculo, veiculoDTO);
        }
    }

    @Transactional
    public void excluirVeiculo(Long id) {
        veiculoRepository.deleteById(id);
    }

    private void atualizarVeiculoFromDTO(Veiculo veiculo, VeiculoDTO veiculoDTO) {
        veiculo.setNome(veiculoDTO.getNome());
        veiculo.setDescricao(veiculoDTO.getDescricao());
        veiculo.setStatus(veiculoDTO.getStatus());
        veiculo.setTipo(veiculoDTO.getTipo());
    }
}
