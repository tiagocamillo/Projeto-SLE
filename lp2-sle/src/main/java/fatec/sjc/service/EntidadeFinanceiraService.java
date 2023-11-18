package fatec.sjc.service;

import java.util.List;

import fatec.sjc.entity.EntidadeFinanceira;
import fatec.sjc.repository.EntidadeFinanceiraRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
@ApplicationScoped
public class EntidadeFinanceiraService {

    private final EntidadeFinanceiraRepository entidadeFinanceiraRepository;

    @Inject
    public EntidadeFinanceiraService(EntidadeFinanceiraRepository entidadeFinanceiraRepository) {
        this.entidadeFinanceiraRepository = entidadeFinanceiraRepository;
    }

    @Transactional
    public EntidadeFinanceira salvarEntidadeFinanceira(EntidadeFinanceira entidadeFinanceira) {
        entidadeFinanceiraRepository.persist(entidadeFinanceira);
        return entidadeFinanceira;
    }

    public List<EntidadeFinanceira> listarEntidadesFinanceiras() {
        return entidadeFinanceiraRepository.listAll();
    }

    public EntidadeFinanceira buscarPorId(Long id) {
        return entidadeFinanceiraRepository.findById(id);
    }

    @Transactional
    public void atualizarEntidadeFinanceira(EntidadeFinanceira entidadeFinanceira) {
        entidadeFinanceiraRepository.persist(entidadeFinanceira);
    }

    @Transactional
    public void excluirEntidadeFinanceira(Long id) {
        entidadeFinanceiraRepository.deleteById(id);
    }
}