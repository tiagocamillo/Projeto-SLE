package fatec.sjc.service;

import java.util.List;

import fatec.sjc.dto.EntidadeFinanceiraDTO;
import fatec.sjc.entity.EntidadeFinanceira;
import fatec.sjc.repository.EntidadeFinanceiraRepository;
import fatec.sjc.repository.LeilaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EntidadeFinanceiraService {

    private final EntidadeFinanceiraRepository entidadeFinanceiraRepository;

    private final LeilaoRepository leilaoRepository;
    
    @Inject
    public EntidadeFinanceiraService(EntidadeFinanceiraRepository entidadeFinanceiraRepository, LeilaoRepository leilaoRepository) {
        this.entidadeFinanceiraRepository = entidadeFinanceiraRepository;
        this.leilaoRepository = leilaoRepository;
    }

    @Transactional
    public EntidadeFinanceira salvarEntidadeFinanceira(EntidadeFinanceiraDTO entidadeFinanceiraDTO) {
    	EntidadeFinanceira entidadeFinanceira = new EntidadeFinanceira();
        entidadeFinanceira.setCnpj(entidadeFinanceiraDTO.getCnpj());
        entidadeFinanceira.setNome(entidadeFinanceiraDTO.getNome());
        entidadeFinanceira.setDetalhesContato(entidadeFinanceiraDTO.getDetalhesContato());

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
    public void atualizarEntidadeFinanceira(EntidadeFinanceiraDTO entidadeFinanceiraDTO) {
        EntidadeFinanceira entidadeFinanceira = new EntidadeFinanceira();
        entidadeFinanceira.setCnpj(entidadeFinanceiraDTO.getCnpj());
        entidadeFinanceira.setNome(entidadeFinanceiraDTO.getNome());
        entidadeFinanceira.setDetalhesContato(entidadeFinanceiraDTO.getDetalhesContato());
        entidadeFinanceiraRepository.persist(entidadeFinanceira);
    }

    @Transactional
    public void excluirEntidadeFinanceira(Long id) {
        entidadeFinanceiraRepository.deleteById(id);
    }
}
