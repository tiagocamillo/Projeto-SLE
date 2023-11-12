package fatec.sjc.service;

import java.util.List;

import fatec.sjc.DTO.EntidadeFinanceiraDTO;
import fatec.sjc.entity.EntidadeFinanceira;
import fatec.sjc.repository.EntidadeFinanceiraRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EntidadesFinanceiraService {

    @Inject
    EntidadeFinanceiraRepository entidadeFinanceiraRepository;

    @Transactional
    public EntidadeFinanceira criarEntidadeFinanceira(EntidadeFinanceiraDTO entidadeFinanceiraDTO) {
        EntidadeFinanceira entidadeFinanceira = new EntidadeFinanceira();
        entidadeFinanceira.setNome(entidadeFinanceiraDTO.getNome());
        entidadeFinanceira.setCnpj(entidadeFinanceiraDTO.getCnpj());

        entidadeFinanceiraRepository.persist(entidadeFinanceira);
        return entidadeFinanceira;
    }

    @Transactional
    public EntidadeFinanceira atualizarEntidadeFinanceira(Long id, EntidadeFinanceiraDTO entidadeFinanceiraDTO) {
        EntidadeFinanceira entidadeFinanceiraExistente = entidadeFinanceiraRepository.findById(id);
        if (entidadeFinanceiraExistente != null) {
            entidadeFinanceiraExistente.setNome(entidadeFinanceiraDTO.getNome());
            entidadeFinanceiraExistente.setCnpj(entidadeFinanceiraDTO.getCnpj());
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
