// MotoService.java
package fatec.sjc.service;

import fatec.sjc.DTO.MotoDTO;
import fatec.sjc.entity.Moto;
import fatec.sjc.repository.MotoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class MotoService {

    @Inject
    MotoRepository motoRepository;

    @Transactional
    public MotoDTO criarMoto(MotoDTO motoDTO) {
        Moto moto = convertToEntity(motoDTO);
        motoRepository.persist(moto);
        return convertToDTO(moto);
    }

    @Transactional
    public MotoDTO atualizarMoto(Long id, MotoDTO motoAtualizadoDTO) {
        Moto motoExistente = motoRepository.findById(id);
        if (motoExistente != null) {
            updateEntityFromDTO(motoExistente, motoAtualizadoDTO);
        }
        return convertToDTO(motoExistente);
    }

    @Transactional
    public void excluirMoto(Long id) {
        Moto motoExistente = motoRepository.findById(id);
        if (motoExistente != null) {
            motoRepository.delete(motoExistente);
        }
    }

    public MotoDTO buscarMotoPorId(Long id) {
        Moto moto = motoRepository.findById(id);
        return (moto != null) ? convertToDTO(moto) : null;
    }

    public List<MotoDTO> listarTodasAsMotos() {
        return motoRepository.listAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private Moto convertToEntity(MotoDTO motoDTO) {
        Moto moto = new Moto();
        moto.setTipo(motoDTO.getTipo());
        moto.setMarca(motoDTO.getMarca());
        moto.setAno(motoDTO.getAno());
        moto.setAcessorios(motoDTO.getAcessorios());
        moto.setCilindrada(motoDTO.getCilindrada());
        return moto;
    }

    private void updateEntityFromDTO(Moto moto, MotoDTO motoDTO) {
        moto.setTipo(motoDTO.getTipo());
        moto.setMarca(motoDTO.getMarca());
        moto.setAno(motoDTO.getAno());
        moto.setAcessorios(motoDTO.getAcessorios());
        moto.setCilindrada(motoDTO.getCilindrada());
    }

    private MotoDTO convertToDTO(Moto moto) {
        MotoDTO motoDTO = new MotoDTO();
        motoDTO.setTipo(moto.getTipo());
        motoDTO.setMarca(moto.getMarca());
        motoDTO.setAno(moto.getAno());
        motoDTO.setAcessorios(moto.getAcessorios());
        motoDTO.setCilindrada(moto.getCilindrada());
        return motoDTO;
    }
}
