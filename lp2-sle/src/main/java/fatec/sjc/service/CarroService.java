package fatec.sjc.service;

import fatec.sjc.entity.Carro;
import fatec.sjc.repository.CarroRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class CarroService {

    private final CarroRepository carroRepository;

    @Inject
    public CarroService(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    @Transactional
    public Carro salvarCarro(Carro carro) {
        carroRepository.persist(carro);
        return carro;
    }

    public List<Carro> listarCarros() {
        return carroRepository.listAll();
    }

    public Carro buscarPorId(Long id) {
        return carroRepository.findById(id);
    }

    @Transactional
    public void atualizarCarro(Carro carro) {
        carroRepository.persist(carro);
    }

    @Transactional
    public void excluirCarro(Long id) {
        carroRepository.deleteById(id);
    }
}