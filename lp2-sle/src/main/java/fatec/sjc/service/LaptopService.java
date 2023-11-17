package fatec.sjc.service;

import fatec.sjc.DTO.LaptopDTO;
import fatec.sjc.entity.Laptop;
import fatec.sjc.repository.LaptopRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class LaptopService {

    @Inject
    LaptopRepository laptopRepository;

    @Transactional
    public LaptopDTO criarLaptop(LaptopDTO laptopDTO) {
        Laptop laptop = convertToEntity(laptopDTO);
        laptopRepository.persist(laptop);
        return convertToDTO(laptop);
    }

    @Transactional
    public LaptopDTO atualizarLaptop(Long id, LaptopDTO laptopAtualizadoDTO) {
        Laptop laptopExistente = laptopRepository.findById(id);
        if (laptopExistente != null) {
            updateEntityFromDTO(laptopExistente, laptopAtualizadoDTO);
        }
        return convertToDTO(laptopExistente);
    }

    @Transactional
    public void excluirLaptop(Long id) {
        Laptop laptopExistente = laptopRepository.findById(id);
        if (laptopExistente != null) {
            laptopRepository.delete(laptopExistente);
        }
    }

    public LaptopDTO buscarLaptopPorId(Long id) {
        Laptop laptop = laptopRepository.findById(id);
        return (laptop != null) ? convertToDTO(laptop) : null;
    }

    public List<LaptopDTO> listarTodosOsLaptops() {
        return laptopRepository.listAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private Laptop convertToEntity(LaptopDTO laptopDTO) {
        Laptop laptop = new Laptop();
        laptop.setMarca(laptopDTO.getMarca());
        laptop.setModelo(laptopDTO.getModelo());
        laptop.setDimensoes(laptopDTO.getDimensoes());
        laptop.setEspecificacoes(laptopDTO.getEspecificacoes());
        laptop.setTamanhoTela(laptopDTO.getTamanhoTela());
        return laptop;
    }

    private void updateEntityFromDTO(Laptop laptop, LaptopDTO laptopDTO) {
        laptop.setMarca(laptopDTO.getMarca());
        laptop.setModelo(laptopDTO.getModelo());
        laptop.setDimensoes(laptopDTO.getDimensoes());
        laptop.setEspecificacoes(laptopDTO.getEspecificacoes());
        laptop.setTamanhoTela(laptopDTO.getTamanhoTela());
    }

    private LaptopDTO convertToDTO(Laptop laptop) {
        LaptopDTO laptopDTO = new LaptopDTO();
        laptopDTO.setMarca(laptop.getMarca());
        laptopDTO.setModelo(laptop.getModelo());
        laptopDTO.setDimensoes(laptop.getDimensoes());
        laptopDTO.setEspecificacoes(laptop.getEspecificacoes());
        laptopDTO.setTamanhoTela(laptop.getTamanhoTela());
        return laptopDTO;
    }
}
