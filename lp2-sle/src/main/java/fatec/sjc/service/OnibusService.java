package fatec.sjc.service;

import fatec.sjc.DTO.OnibusDTO;
import fatec.sjc.entity.Onibus;
import fatec.sjc.repository.OnibusRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class OnibusService {

    @Inject
    OnibusRepository onibusRepository;

    @Transactional
    public OnibusDTO criarOnibus(OnibusDTO onibusDTO) {
        Onibus onibus = convertToEntity(onibusDTO);
        onibusRepository.persist(onibus);
        return convertToDTO(onibus);
    }

    @Transactional
    public OnibusDTO atualizarOnibus(Long id, OnibusDTO onibusAtualizadoDTO) {
        Onibus onibusExistente = onibusRepository.findById(id);
        if (onibusExistente != null) {
            updateEntityFromDTO(onibusExistente, onibusAtualizadoDTO);
        }
        return convertToDTO(onibusExistente);
    }

    @Transactional
    public void excluirOnibus(Long id) {
        Onibus onibusExistente = onibusRepository.findById(id);
        if (onibusExistente != null) {
            onibusRepository.delete(onibusExistente);
        }
    }

    public OnibusDTO buscarOnibusPorId(Long id) {
        Onibus onibus = onibusRepository.findById(id);
        return (onibus != null) ? convertToDTO(onibus) : null;
    }

    public List<OnibusDTO> listarTodosOsOnibus() {
        return onibusRepository.listAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private Onibus convertToEntity(OnibusDTO onibusDTO) {
        Onibus onibus = new Onibus();

        onibus.setCapacidadePassageiros(onibusDTO.getCapacidadePassageiros());
        return onibus;
    }

    private void updateEntityFromDTO(Onibus onibus, OnibusDTO onibusDTO) {
        onibus.setCapacidadePassageiros(onibusDTO.getCapacidadePassageiros());
    }

    private OnibusDTO convertToDTO(Onibus onibus) {
        OnibusDTO onibusDTO = new OnibusDTO();
        onibusDTO.setCapacidadePassageiros(onibus.getCapacidadePassageiros());
        return onibusDTO;
    }
}