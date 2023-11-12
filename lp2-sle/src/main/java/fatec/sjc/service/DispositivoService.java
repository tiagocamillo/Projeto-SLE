package fatec.sjc.service;

import fatec.sjc.DTO.DispositivoDTO;
import fatec.sjc.entity.Dispositivo;
import fatec.sjc.repository.DispositivoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class DispositivoService {

    @Inject
    DispositivoRepository dispositivoRepository;

    @Transactional
    public Dispositivo criarDispositivo(DispositivoDTO dispositivoDTO) {
        Dispositivo dispositivo = new Dispositivo();
        // Convert DTO to Entity and set other fields
        dispositivo.setNome(dispositivoDTO.getNome());
        dispositivo.setDescricao(dispositivoDTO.getDescricao());
        dispositivo.setCondicao(dispositivoDTO.getCondicao());
        dispositivo.setHistoricoReparo(dispositivoDTO.getHistoricoReparo());
        dispositivo.setMarca(dispositivoDTO.getMarca());
        dispositivo.setModelo(dispositivoDTO.getModelo());
        dispositivo.setDimensoes(dispositivoDTO.getDimensoes());
        dispositivo.setEspecificacoes(dispositivoDTO.getEspecificacoes());

        dispositivoRepository.persist(dispositivo);
        return dispositivo;
    }

    @Transactional
    public Dispositivo atualizarDispositivo(Long id, DispositivoDTO dispositivoDTO) {
        Dispositivo dispositivoExistente = dispositivoRepository.findById(id);
        if (dispositivoExistente != null) {
            // Update fields from DTO to Entity
            dispositivoExistente.setNome(dispositivoDTO.getNome());
            dispositivoExistente.setDescricao(dispositivoDTO.getDescricao());
            dispositivoExistente.setCondicao(dispositivoDTO.getCondicao());
            dispositivoExistente.setHistoricoReparo(dispositivoDTO.getHistoricoReparo());
            dispositivoExistente.setMarca(dispositivoDTO.getMarca());
            dispositivoExistente.setModelo(dispositivoDTO.getModelo());
            dispositivoExistente.setDimensoes(dispositivoDTO.getDimensoes());
            dispositivoExistente.setEspecificacoes(dispositivoDTO.getEspecificacoes());
        }
        return dispositivoExistente;
    }

    @Transactional
    public void excluirDispositivo(Long id) {
        Dispositivo dispositivoExistente = dispositivoRepository.findById(id);
        if (dispositivoExistente != null) {
            dispositivoRepository.delete(dispositivoExistente);
        }
    }

    public Dispositivo buscarDispositivoPorId(Long id) {
        return dispositivoRepository.findById(id);
    }

    public List<Dispositivo> listarTodosOsDispositivos() {
        return dispositivoRepository.listAll();
    }
}
