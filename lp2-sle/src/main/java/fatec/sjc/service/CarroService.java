package fatec.sjc.service;

import fatec.sjc.entity.Carro;
import fatec.sjc.repository.CarroRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class CarroService {

    @Inject
    CarroRepository carroRepository;

    @Transactional
    public Carro criarCarro(Carro carro) {
        carroRepository.persist(carro);
        return carro;
    }

    @Transactional
    public Carro atualizarCarro(Long id, Carro carro) {
        Carro carroExistente = carroRepository.findById(id);
        if (carroExistente != null) {
            carroExistente.setNumeroPortas(carro.getNumeroPortas());
            carroRepository.persist(carroExistente);
            return carroExistente;
        } else {
            return null;
        }
    }

    @Transactional
    public void deletarCarro(Long id) {
        Carro carro = carroRepository.findById(id);
        if (carro != null) {
            carroRepository.delete(carro);
        }
    }

    public Carro buscarCarroPorId(Long id) {
        return carroRepository.findById(id);
    }

    public List<Carro> listarCarros() {
        return carroRepository.listAll();
    }
}
