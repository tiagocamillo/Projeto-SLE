package fatec.sjc.service;

import fatec.sjc.entity.Dispositivo;
import fatec.sjc.repository.DispositivoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class DispositivoService {

    private final DispositivoRepository dispositivoRepository;

    public DispositivoService(DispositivoRepository dispositivoRepository) {
        this.dispositivoRepository = dispositivoRepository;
    }

    @Transactional
    public Dispositivo salvarDispositivo(Dispositivo dispositivo) {
        dispositivoRepository.persist(dispositivo);
        return dispositivo;
    }

    public List<Dispositivo> listarDispositivos() {
        return dispositivoRepository.listAll();
    }

    public Dispositivo buscarPorId(Long id) {
        return dispositivoRepository.findById(id);
    }

    @Transactional
    public void atualizarDispositivo(Dispositivo dispositivo) {
        dispositivoRepository.persist(dispositivo);
    }

    @Transactional
    public void excluirDispositivo(Long id) {
        dispositivoRepository.deleteById(id);
    }
}