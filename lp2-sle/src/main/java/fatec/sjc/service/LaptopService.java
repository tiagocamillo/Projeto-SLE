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
    LaptopRepository laptopRepository;

    @Transactional
    public Laptop criarLaptop(Laptop laptop) {
        laptopRepository.persist(laptop);
        return laptop;
    }

    @Transactional
    public Laptop atualizarLaptop(Long id, Laptop laptopAtualizado) {
        Laptop laptopExistente = laptopRepository.findById(id);
        if (laptopExistente != null) {
            laptopExistente.setMarca(laptopAtualizado.getMarca());
            laptopExistente.setModelo(laptopAtualizado.getModelo());
            laptopExistente.setDimensoes(laptopAtualizado.getDimensoes());
            laptopExistente.setEspecificacoes(laptopAtualizado.getEspecificacoes());
            laptopExistente.setTamanhoTela(laptopAtualizado.getTamanhoTela());
        }
        return laptopExistente;
    }

    @Transactional
    public void excluirLaptop(Long id) {
        Laptop laptopExistente = laptopRepository.findById(id);
        if (laptopExistente != null) {
            laptopRepository.delete(laptopExistente);
        }
    }

    public Laptop buscarLaptopPorId(Long id) {
        return laptopRepository.findById(id);
    }

    public List<Laptop> listarTodosOsLaptops() {
        return laptopRepository.listAll();
    }
}
