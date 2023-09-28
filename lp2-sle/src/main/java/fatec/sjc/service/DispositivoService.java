package fatec.sjc.service;

import java.util.List;

import fatec.sjc.entity.Dispositivo;
import fatec.sjc.repository.DispositivoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

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
        	dispositivoExistente.setTipoProduto(dispositivoAtualizado.getTipoProduto());
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