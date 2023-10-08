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
    public Moto atualizarMoto(Long id, Moto motoAtualizada) {
        Moto motoExistente = motoRepository.findById(id);
        if (motoExistente != null) {
            motoExistente.setCilindrada(motoAtualizada.getCilindrada());
        }
        return motoExistente;
    }

    @Transactional
    public void excluirMoto(Long id) {
        Moto motoExistente = motoRepository.findById(id);
        if (motoExistente != null) {
            motoRepository.delete(motoExistente);
        }
    }

    public Moto buscarMotoPorId(Long id) {
        return motoRepository.findById(id);
    }

    public List<Moto> listarTodasAsMotos() {
        return motoRepository.listAll();
    }
}
