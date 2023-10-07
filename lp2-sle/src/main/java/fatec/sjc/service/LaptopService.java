package fatec.sjc.service;

import fatec.sjc.entity.Laptop;
import fatec.sjc.repository.LaptopRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class LaptopService {

    @Inject
    private LaptopRepository laptopRepository;

    @Transactional
    public Laptop criarLaptop(Laptop laptop) {
        laptopRepository.persist(laptop);
        return laptop;
    }

    @Transactional
    public Laptop atualizarLaptop(Long id, Laptop laptop) {
        Laptop laptopExistente = laptopRepository.findById(id);
        if (laptopExistente != null) {
            laptopRepository.persist(laptopExistente);
            return laptopExistente;
        } else {
            return null;
        }
    }

    @Transactional
    public void deletarLaptop(Long id) {
        Laptop laptop = laptopRepository.findById(id);
        if (laptop != null) {
            laptopRepository.delete(laptop);
        }
    }

    public Laptop buscarLaptopPorId(Long id) {
        return laptopRepository.findById(id);
    }

    public List<Laptop> listarLaptops() {
        return laptopRepository.listAll();
    }
}
