package fatec.sjc.service;

import fatec.sjc.DTO.VeiculoDTO;
import fatec.sjc.entity.Veiculo;
import fatec.sjc.repository.VeiculoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
@ApplicationScoped

public class VeiculoService {

    @Inject
    VeiculoRepository veiculoRepository;

    @Transactional
    public VeiculoDTO criarVeiculo(VeiculoDTO veiculoDTO) {
        Veiculo veiculo = convertToEntity(veiculoDTO);
        veiculoRepository.persist(veiculo);
        return convertToDTO(veiculo);
    }

    @Transactional
    public VeiculoDTO atualizarVeiculo(Long id, VeiculoDTO veiculoAtualizadoDTO) {
        Veiculo veiculoExistente = veiculoRepository.findById(id);
        if (veiculoExistente != null) {
            updateEntityFromDTO(veiculoExistente, veiculoAtualizadoDTO);
        }
        return convertToDTO(veiculoExistente);
    }

    @Transactional
    public void excluirVeiculo(Long id) {
        Veiculo veiculoExistente = veiculoRepository.findById(id);
        if (veiculoExistente != null) {
            veiculoRepository.delete(veiculoExistente);
        }
    }

    public VeiculoDTO buscarVeiculoPorId(Long id) {
        Veiculo veiculo = veiculoRepository.findById(id);
        return (veiculo != null) ? convertToDTO(veiculo) : null;
    }

    public List<VeiculoDTO> listarTodosOsVeiculos() {
        return veiculoRepository.listAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private Veiculo convertToEntity(VeiculoDTO veiculoDTO) {
        Veiculo veiculo = new Veiculo();
        veiculo.setTipo(veiculoDTO.getTipo());
        veiculo.setMarca(veiculoDTO.getMarca());
        veiculo.setAno(veiculoDTO.getAno());
        veiculo.setAcessorios(veiculoDTO.getAcessorios());
        return veiculo;
    }

    private void updateEntityFromDTO(Veiculo veiculo, VeiculoDTO veiculoDTO) {
        veiculo.setTipo(veiculoDTO.getTipo());
        veiculo.setMarca(veiculoDTO.getMarca());
        veiculo.setAno(veiculoDTO.getAno());
        veiculo.setAcessorios(veiculoDTO.getAcessorios());
    }

    private VeiculoDTO convertToDTO(Veiculo veiculo) {
        VeiculoDTO veiculoDTO = new VeiculoDTO();
        veiculoDTO.setId(veiculo.id);
        veiculoDTO.setTipo(veiculo.getTipo());
        veiculoDTO.setMarca(veiculo.getMarca());
        veiculoDTO.setAno(veiculo.getAno());
        veiculoDTO.setAcessorios(veiculo.getAcessorios());
        return veiculoDTO;
    }
}




