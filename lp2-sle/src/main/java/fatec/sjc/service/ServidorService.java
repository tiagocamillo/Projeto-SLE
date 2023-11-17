package fatec.sjc.service;

import fatec.sjc.DTO.ServidorDTO;
import fatec.sjc.entity.Servidor;
import fatec.sjc.repository.ServidorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ServidorService {

    @Inject
    ServidorRepository servidorRepository;

    @Transactional
    public ServidorDTO criarServidor(ServidorDTO servidorDTO) {
        Servidor servidor = convertToEntity(servidorDTO);
        servidorRepository.persist(servidor);
        return convertToDTO(servidor);
    }

    @Transactional
    public ServidorDTO atualizarServidor(Long id, ServidorDTO servidorAtualizadoDTO) {
        Servidor servidorExistente = servidorRepository.findById(id);
        if (servidorExistente != null) {
            updateEntityFromDTO(servidorExistente, servidorAtualizadoDTO);
        }
        return convertToDTO(servidorExistente);
    }

    @Transactional
    public void excluirServidor(Long id) {
        Servidor servidorExistente = servidorRepository.findById(id);
        if (servidorExistente != null) {
            servidorRepository.delete(servidorExistente);
        }
    }

    public ServidorDTO buscarServidorPorId(Long id) {
        Servidor servidor = servidorRepository.findById(id);
        return (servidor != null) ? convertToDTO(servidor) : null;
    }

    public List<ServidorDTO> listarTodosOsServidores() {
        return servidorRepository.listAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private Servidor convertToEntity(ServidorDTO servidorDTO) {
        Servidor servidor = new Servidor();
        servidor.setMarca(servidorDTO.getMarca());
        servidor.setModelo(servidorDTO.getModelo());
        servidor.setDimensoes(servidorDTO.getDimensoes());
        servidor.setEspecificacoes(servidorDTO.getEspecificacoes());
        servidor.setProcessador(servidorDTO.getProcessador());
        servidor.setCapacidadeArmazenamento(servidorDTO.getCapacidadeArmazenamento());
        return servidor;
    }

    private void updateEntityFromDTO(Servidor servidor, ServidorDTO servidorDTO) {
        servidor.setMarca(servidorDTO.getMarca());
        servidor.setModelo(servidorDTO.getModelo());
        servidor.setDimensoes(servidorDTO.getDimensoes());
        servidor.setEspecificacoes(servidorDTO.getEspecificacoes());
        servidor.setProcessador(servidorDTO.getProcessador());
        servidor.setCapacidadeArmazenamento(servidorDTO.getCapacidadeArmazenamento());
    }

    private ServidorDTO convertToDTO(Servidor servidor) {
        ServidorDTO servidorDTO = new ServidorDTO();
        servidorDTO.setMarca(servidor.getMarca());
        servidorDTO.setModelo(servidor.getModelo());
        servidorDTO.setDimensoes(servidor.getDimensoes());
        servidorDTO.setEspecificacoes(servidor.getEspecificacoes());
        servidorDTO.setProcessador(servidor.getProcessador());
        servidorDTO.setCapacidadeArmazenamento(servidor.getCapacidadeArmazenamento());
        return servidorDTO;
    }
}
