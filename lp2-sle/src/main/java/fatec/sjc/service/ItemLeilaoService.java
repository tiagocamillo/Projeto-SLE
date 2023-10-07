package fatec.sjc.service;

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
    public ItemLeilao criarItemLeilao(ItemLeilao itemLeilao) {
        itemLeilaoRepository.persist(itemLeilao);
        return itemLeilao;
    }

    @Transactional
    public ItemLeilao atualizarItemLeilao(Long id, ItemLeilao itemLeilaoAtualizado) {
        ItemLeilao itemLeilaoExistente = itemLeilaoRepository.findById(id);
        if (itemLeilaoExistente != null) {
            // Atualize os campos necessários
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
