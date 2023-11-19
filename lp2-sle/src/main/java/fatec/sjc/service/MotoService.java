package fatec.sjc.service;

import java.util.List;

import fatec.sjc.dto.MotoDTO;
import fatec.sjc.entity.Moto;
import fatec.sjc.repository.MotoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MotoService {

    private final MotoRepository motoRepository;

    @Inject
    public MotoService(MotoRepository motoRepository) {
        this.motoRepository = motoRepository;
    }

    @Transactional
    public Moto salvarMoto(MotoDTO motoDTO) {
        Moto moto = new Moto();
        moto.setPossuiCarenagem(motoDTO.isPossuiCarenagem());
        moto.setTipoMotocicleta(motoDTO.getTipoMotocicleta());
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
    public void atualizarMoto(MotoDTO motoDTO) {
        Moto moto = new Moto();
        moto.setPossuiCarenagem(motoDTO.isPossuiCarenagem());
        moto.setTipoMotocicleta(motoDTO.getTipoMotocicleta());
        motoRepository.persist(moto);
    }

    @Transactional
    public void excluirMoto(Long id) {
        motoRepository.deleteById(id);
    }
}
