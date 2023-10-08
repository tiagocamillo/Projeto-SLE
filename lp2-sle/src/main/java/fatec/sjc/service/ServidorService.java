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
    ServidorRepository servidorRepository;

    @Transactional
    public Servidor criarServidor(Servidor servidor) {
        servidorRepository.persist(servidor);
        return servidor;
    }

    @Transactional
    public Servidor atualizarServidor(Long id, Servidor servidorAtualizado) {
        Servidor servidorExistente = servidorRepository.findById(id);
        if (servidorExistente != null) {
            servidorExistente.setProcessador(servidorAtualizado.getProcessador());
            servidorExistente.setCapacidadeArmazenamento(servidorAtualizado.getCapacidadeArmazenamento());
        }
        return servidorExistente;
    }

    @Transactional
    public void excluirServidor(Long id) {
        Servidor servidorExistente = servidorRepository.findById(id);
        if (servidorExistente != null) {
            servidorRepository.delete(servidorExistente);
        }
    }

    public Servidor buscarServidorPorId(Long id) {
        return servidorRepository.findById(id);
    }

    public List<Servidor> listarTodosOsServidores() {
        return servidorRepository.listAll();
    }
}
