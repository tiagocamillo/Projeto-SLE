package fatec.sjc.service;

import java.util.List;

import fatec.sjc.dto.ServidorDTO;
import fatec.sjc.entity.Servidor;
import fatec.sjc.repository.ServidorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ServidorService {

    private final ServidorRepository servidorRepository;

    @Inject
    public ServidorService(ServidorRepository servidorRepository) {
        this.servidorRepository = servidorRepository;
    }

    @Transactional
    public Servidor salvarServidor(ServidorDTO servidorDTO) {
        Servidor servidor = new Servidor();
        servidor.setTamanhoRack(servidorDTO.getTamanhoRack());
        servidor.setQuantidadeProcessadores(servidorDTO.getQuantidadeProcessadores());
        servidorRepository.persist(servidor);
        return servidor;
    }

    public List<Servidor> listarServidores() {
        return servidorRepository.listAll();
    }

    public Servidor buscarPorId(Long id) {
        return servidorRepository.findById(id);
    }

    @Transactional
    public void atualizarServidor(ServidorDTO servidorDTO) {
        Servidor servidor = new Servidor();
        servidor.setTamanhoRack(servidorDTO.getTamanhoRack());
        servidor.setQuantidadeProcessadores(servidorDTO.getQuantidadeProcessadores());
        servidorRepository.persist(servidor);
    }

    @Transactional
    public void excluirServidor(Long id) {
        servidorRepository.deleteById(id);
    }
}
