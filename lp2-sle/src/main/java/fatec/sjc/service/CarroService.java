package fatec.sjc.service;

import fatec.sjc.DTO.CarroDTO;
import fatec.sjc.entity.Carro;
import fatec.sjc.repository.CarroRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CarroService {

    @Inject
    CarroRepository carroRepository;

    @Transactional
    public CarroDTO criarCarro(CarroDTO carroDTO) {
        Carro carro = convertToEntity(carroDTO);
        carroRepository.persist(carro);
        return convertToDTO(carro);
    }

    @Transactional
    public CarroDTO atualizarCarro(Long id, CarroDTO carroAtualizadoDTO) {
        Carro carroExistente = carroRepository.findById(id);
        if (carroExistente != null) {
            updateEntityFromDTO(carroExistente, carroAtualizadoDTO);
        }
        return convertToDTO(carroExistente);
    }

    @Transactional
    public void excluirCarro(Long id) {
        Carro carroExistente = carroRepository.findById(id);
        if (carroExistente != null) {
            carroRepository.delete(carroExistente);
        }
    }

    public CarroDTO buscarCarroPorId(Long id) {
        Carro carro = carroRepository.findById(id);
        return (carro != null) ? convertToDTO(carro) : null;
    }

    public List<CarroDTO> listarTodosOsCarros() {
        return carroRepository.listAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private Carro convertToEntity(CarroDTO carroDTO) {
        Carro carro = new Carro();
        carro.setTipo(carroDTO.getTipo());
        carro.setMarca(carroDTO.getMarca());
        carro.setAno(carroDTO.getAno());
        carro.setAcessorios(carroDTO.getAcessorios());

        carro.setNumeroPortas(carroDTO.getNumeroPortas());
        return carro;
    }

    private void updateEntityFromDTO(Carro carro, CarroDTO carroDTO) {
        carro.setTipo(carroDTO.getTipo());
        carro.setMarca(carroDTO.getMarca());
        carro.setAno(carroDTO.getAno());
        carro.setAcessorios(carroDTO.getAcessorios());

        carro.setNumeroPortas(carroDTO.getNumeroPortas());
    }

    private CarroDTO convertToDTO(Carro carro) {
        CarroDTO carroDTO = new CarroDTO();
        // Copy common fields to VeiculoDTO
        carroDTO.setTipo(carro.getTipo());
        carroDTO.setMarca(carro.getMarca());
        carroDTO.setAno(carro.getAno());
        carroDTO.setAcessorios(carro.getAcessorios());

        carroDTO.setNumeroPortas(carro.getNumeroPortas());
        return carroDTO;
    }
}
