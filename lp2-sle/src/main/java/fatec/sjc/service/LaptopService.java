package fatec.sjc.service;

import fatec.sjc.dto.LaptopDTO;
import fatec.sjc.entity.Laptop;
import fatec.sjc.repository.LaptopRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class LaptopService {

    private final LaptopRepository laptopRepository;

    @Inject
    public LaptopService(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @Transactional
    public Laptop salvarLaptop(LaptopDTO laptopDTO) {
        Laptop laptop = new Laptop();
        laptop.setTamanhoTela(laptopDTO.getTamanhoTela());
        laptop.setProcessador(laptopDTO.getProcessador());
        laptopRepository.persist(laptop);
        return laptop;
    }

    public List<Laptop> listarLaptops() {
        return laptopRepository.listAll();
    }

    public Laptop buscarPorId(Long id) {
        return laptopRepository.findById(id);
    }

    @Transactional
    public void atualizarLaptop(LaptopDTO laptopDTO) {
        Laptop laptop = new Laptop();
        laptop.setTamanhoTela(laptopDTO.getTamanhoTela());
        laptop.setProcessador(laptopDTO.getProcessador());
        laptopRepository.persist(laptop);
    }

    @Transactional
    public void excluirLaptop(Long id) {
        laptopRepository.deleteById(id);
    }
}
