package fatec.sjc.service;

import fatec.sjc.entity.Servidor;
import fatec.sjc.repository.ServidorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ServidorService {

    @Inject
    private ServidorRepository servidorRepository;

    @Transactional
    public Servidor criarServidor(Servidor servidor) {
        servidorRepository.persist(servidor);
        return servidor;
    }

    @Transactional
    public Servidor atualizarServidor(Long id, Servidor servidor) {
        Servidor servidorExistente = servidorRepository.findById(id);
        if (servidorExistente != null) {
            // Atualize os atributos do servidor conforme necessário
            servidorRepository.persist(servidorExistente);
            return servidorExistente;
        } else {
            return null;
        }
    }

    @Transactional
    public void deletarServidor(Long id) {
        Servidor servidor = servidorRepository.findById(id);
        if (servidor != null) {
            servidorRepository.delete(servidor);
        }
    }

    public Servidor buscarServidorPorId(Long id) {
        return servidorRepository.findById(id);
    }

    public List<Servidor> listarServidores() {
        return servidorRepository.listAll();
    }
}
