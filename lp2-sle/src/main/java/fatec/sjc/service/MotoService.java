package fatec.sjc.service;

import fatec.sjc.entity.Moto;
import fatec.sjc.repository.MotoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class MotoService {

    @Inject
    MotoRepository motoRepository;

    @Transactional
    public Moto criarMoto(Moto moto) {
        motoRepository.persist(moto);
        return moto;
    }

    @Transactional
    public Moto atualizarMoto(Long id, Moto moto) {
        motoRepository.persist(moto);
        return moto;
    }

    @Transactional
    public void deletarMoto(Long id) {
        Moto moto = motoRepository.findById(id);
        if (moto != null) {
            motoRepository.delete(moto);
        }
    }

    public Moto buscarMotoPorId(Long id) {
        return motoRepository.findById(id);
    }

    public List<Moto> listarMotos() {
        return motoRepository.listAll();
    }

}
