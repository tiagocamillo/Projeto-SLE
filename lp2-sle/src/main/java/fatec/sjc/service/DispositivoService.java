package fatec.sjc.service;

import fatec.sjc.DTO.DispositivoDTO;
import fatec.sjc.entity.Dispositivo;
import fatec.sjc.repository.DispositivoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class DispositivoService {

    @Inject
    DispositivoRepository dispositivoRepository;

    @Transactional
    public DispositivoDTO criarDispositivo(DispositivoDTO dispositivoDTO) {
        Dispositivo dispositivo = convertToEntity(dispositivoDTO);
        dispositivoRepository.persist(dispositivo);
        return convertToDTO(dispositivo);
    }

    @Transactional
    public DispositivoDTO atualizarDispositivo(Long id, DispositivoDTO dispositivoAtualizadoDTO) {
        Dispositivo dispositivoExistente = dispositivoRepository.findById(id);
        if (dispositivoExistente != null) {
            updateEntityFromDTO(dispositivoExistente, dispositivoAtualizadoDTO);
        }
        return convertToDTO(dispositivoExistente);
    }

    @Transactional
    public void excluirDispositivo(Long id) {
        Dispositivo dispositivoExistente = dispositivoRepository.findById(id);
        if (dispositivoExistente != null) {
            dispositivoRepository.delete(dispositivoExistente);
        }
    }

    public DispositivoDTO buscarDispositivoPorId(Long id) {
        Dispositivo dispositivo = dispositivoRepository.findById(id);
        return (dispositivo != null) ? convertToDTO(dispositivo) : null;
    }

    public List<DispositivoDTO> listarTodosOsDispositivos() {
        return dispositivoRepository.listAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private Dispositivo convertToEntity(DispositivoDTO dispositivoDTO) {
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setMarca(dispositivoDTO.getMarca());
        dispositivo.setModelo(dispositivoDTO.getModelo());
        dispositivo.setDimensoes(dispositivoDTO.getDimensoes());
        dispositivo.setEspecificacoes(dispositivoDTO.getEspecificacoes());
        return dispositivo;
    }

    private void updateEntityFromDTO(Dispositivo dispositivo, DispositivoDTO dispositivoDTO) {
        dispositivo.setMarca(dispositivoDTO.getMarca());
        dispositivo.setModelo(dispositivoDTO.getModelo());
        dispositivo.setDimensoes(dispositivoDTO.getDimensoes());
        dispositivo.setEspecificacoes(dispositivoDTO.getEspecificacoes());
    }

    private DispositivoDTO convertToDTO(Dispositivo dispositivo) {
        DispositivoDTO dispositivoDTO = new DispositivoDTO();
        dispositivoDTO.setMarca(dispositivo.getMarca());
        dispositivoDTO.setModelo(dispositivo.getModelo());
        dispositivoDTO.setDimensoes(dispositivo.getDimensoes());
        dispositivoDTO.setEspecificacoes(dispositivo.getEspecificacoes());
        return dispositivoDTO;
    }
}
