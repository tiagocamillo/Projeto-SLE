package fatec.sjc.service;

import fatec.sjc.DTO.ItemLeilaoDTO;
import fatec.sjc.entity.ItemLeilao;
import fatec.sjc.repository.ItemLeilaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ItemLeilaoService {

    @Inject
    ItemLeilaoRepository itemLeilaoRepository;

    @Transactional
    public ItemLeilao criarItemLeilao(ItemLeilaoDTO itemLeilaoDTO) {
        ItemLeilao itemLeilao = new ItemLeilao();
        itemLeilao.setNome(itemLeilaoDTO.getNome());
        itemLeilao.setDescricao(itemLeilaoDTO.getDescricao());
        itemLeilao.setCondicao(itemLeilaoDTO.getCondicao());
        itemLeilao.setHistoricoReparo(itemLeilaoDTO.getHistoricoReparo());

        itemLeilaoRepository.persist(itemLeilao);
        return itemLeilao;
    }

    @Transactional
    public ItemLeilao atualizarItemLeilao(Long id, ItemLeilaoDTO itemLeilaoDTO) {
        ItemLeilao itemLeilaoExistente = itemLeilaoRepository.findById(id);
        if (itemLeilaoExistente != null) {
            itemLeilaoExistente.setNome(itemLeilaoDTO.getNome());
            itemLeilaoExistente.setDescricao(itemLeilaoDTO.getDescricao());
            itemLeilaoExistente.setCondicao(itemLeilaoDTO.getCondicao());
            itemLeilaoExistente.setHistoricoReparo(itemLeilaoDTO.getHistoricoReparo());
        }
        return itemLeilaoExistente;
    }

    @Transactional
    public void excluirItemLeilao(Long id) {
        ItemLeilao itemLeilaoExistente = itemLeilaoRepository.findById(id);
        if (itemLeilaoExistente != null) {
            itemLeilaoRepository.delete(itemLeilaoExistente);
        }
    }

    public ItemLeilao buscarItemLeilaoPorId(Long id) {
        return itemLeilaoRepository.findById(id);
    }

    public List<ItemLeilao> listarTodosOsItensLeilao() {
        return itemLeilaoRepository.listAll();
    }
}
