package fatec.sjc.service;

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
    public Dispositivo criarDispositivo(Dispositivo dispositivo) {
        dispositivoRepository.persist(dispositivo);
        return dispositivo;
    }

    @Transactional
    public Dispositivo atualizarDispositivo(Long id, Dispositivo dispositivoAtualizado) {
        Dispositivo dispositivoExistente = dispositivoRepository.findById(id);
        if (dispositivoExistente != null) {
        	dispositivoExistente.setNome(dispositivoAtualizado.getNome());
        	dispositivoExistente.setDescricao(dispositivoAtualizado.getDescricao());
        	dispositivoExistente.setMarca(dispositivoAtualizado.getMarca());
        	dispositivoExistente.setModelo(dispositivoAtualizado.getModelo());
        	dispositivoExistente.setDimensoes(dispositivoAtualizado.getDimensoes());
        	dispositivoExistente.setCondicao(dispositivoAtualizado.getCondicao());
        	dispositivoExistente.setEspecificacoes(dispositivoAtualizado.getEspecificacoes());
        	dispositivoExistente.setHistoricoReparo(dispositivoAtualizado.getHistoricoReparo());
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
