package fatec.sjc.service;

import fatec.sjc.entity.EntidadeFinanceira;
import fatec.sjc.repository.EntidadeFinanceiraRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class EntidadesFinanceiraService {

    @Inject
    EntidadeFinanceiraRepository entidadeFinanceiraRepository;

    @Transactional
    public EntidadeFinanceira criarEntidadesFinanceira(EntidadeFinanceira entidadeFinanceira) {
        entidadeFinanceiraRepository.persist(entidadeFinanceira);
        return entidadeFinanceira;
    }

    @Transactional
    public EntidadeFinanceira atualizarEntidadeFinanceira(Long id, EntidadeFinanceira entidadeFinanceiraAtualizado) {
        EntidadeFinanceira entidadeFinanceiraExistente = entidadeFinanceiraRepository.findById(id);
        if (entidadeFinanceiraExistente != null) {
            entidadeFinanceiraExistente.setNome(entidadeFinanceiraAtualizado.getNome());
            entidadeFinanceiraExistente.setCnpj(entidadeFinanceiraAtualizado.getCnpj());
        }
        return entidadeFinanceiraExistente;
    }

    @Transactional
    public void excluirEntidadeFinanceira(Long id) {
        EntidadeFinanceira entidadeFinanceiraExistente = entidadeFinanceiraRepository.findById(id);
        if (entidadeFinanceiraExistente != null) {
            entidadeFinanceiraRepository.delete(entidadeFinanceiraExistente);
        }
    }

    public EntidadeFinanceira buscarEntidadeFinanceiraPorId(Long id) {
        return entidadeFinanceiraRepository.findById(id);
    }

    public List<EntidadeFinanceira> listarTodasAsEntidadesFinanceiras() {
        return entidadeFinanceiraRepository.listAll();
    }
}
