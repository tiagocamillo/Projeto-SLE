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
    public Carro atualizarCarro(Long id, Carro carroAtualizado) {
        Carro carroExistente = carroRepository.findById(id);
        if (carroExistente != null) {
            carroExistente.setNumeroPortas(carroAtualizado.getNumeroPortas());
        }
        return carroExistente;
    }

    @Transactional
    public void excluirCarro(Long id) {
        Carro carroExistente = carroRepository.findById(id);
        if (carroExistente != null) {
            carroRepository.delete(carroExistente);
        }
    }

    public Carro buscarCarroPorId(Long id) {
        return carroRepository.findById(id);
    }

    public List<Carro> listarTodosOsCarros() {
        return carroRepository.listAll();
    }
}
