// MotoService.java
package fatec.sjc.service;

import fatec.sjc.entity.Moto;
import fatec.sjc.repository.MotoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;


@ApplicationScoped
public class MotoService {

    private final MotoRepository motoRepository;

    @Inject
    public MotoService(MotoRepository motoRepository) {
        this.motoRepository = motoRepository;
    }

    @Transactional
    public Moto salvarMoto(Moto moto) {
        motoRepository.persist(moto);
        return moto;
    }

    public List<Moto> listarMotos() {
        return motoRepository.listAll();
    }

    public Moto buscarPorId(Long id) {
        return motoRepository.findById(id);
    }

    @Transactional
    public void atualizarMoto(Moto moto) {
        motoRepository.persist(moto);
    }

    @Transactional
    public void excluirMoto(Long id) {
        motoRepository.deleteById(id);
    }
}
