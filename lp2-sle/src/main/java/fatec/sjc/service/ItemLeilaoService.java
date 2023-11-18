package fatec.sjc.service;

import fatec.sjc.DTO.ItemLeilaoDTO;
import fatec.sjc.entity.ItemLeilao;
import fatec.sjc.entity.Leilao;
import fatec.sjc.repository.ItemLeilaoRepository;
import fatec.sjc.repository.LeilaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ItemLeilaoService {

    @Inject
    ItemLeilaoRepository itemLeilaoRepository;

    @Inject
    LeilaoRepository leilaoRepository;
    @Transactional
    public ItemLeilao criarItemLeilao(ItemLeilaoDTO itemLeilaoDTO) {
        ItemLeilao itemLeilao = new ItemLeilao();
        atualizarEntidadeComDTO(itemLeilao, itemLeilaoDTO);
        itemLeilaoRepository.persist(itemLeilao);
        return itemLeilao;
    }

    @Transactional
    public ItemLeilao atualizarItemLeilao(Long id, ItemLeilaoDTO itemLeilaoDTO) {
        ItemLeilao itemLeilaoExistente = itemLeilaoRepository.findById(id);
        if (itemLeilaoExistente != null) {
            atualizarEntidadeComDTO(itemLeilaoExistente, itemLeilaoDTO);
        }
        return itemLeilaoExistente;
    }

    @Transactional
    public boolean excluirItemLeilao(Long id) {
        ItemLeilao itemLeilaoExistente = itemLeilaoRepository.findById(id);
        if (itemLeilaoExistente != null) {
            itemLeilaoRepository.delete(itemLeilaoExistente);
        }
        return false;
    }

    public ItemLeilao buscarItemLeilaoPorId(Long id) {
        return itemLeilaoRepository.findById(id);
    }

    public List<ItemLeilao> listarTodosOsItensLeilao() {
        return itemLeilaoRepository.listAll();
    }

    private void atualizarEntidadeComDTO(ItemLeilao itemLeilao, ItemLeilaoDTO itemLeilaoDTO) {
        itemLeilao.setNome(itemLeilaoDTO.getNome());
        itemLeilao.setDescricao(itemLeilaoDTO.getDescricao());
        itemLeilao.setCondicao(itemLeilaoDTO.getCondicao());
        itemLeilao.setHistoricoReparo(itemLeilaoDTO.getHistoricoReparo());

        if (itemLeilaoDTO.getIdLeilao() != null) {
            Leilao leilao = leilaoRepository.findById(itemLeilaoDTO.getIdLeilao());
            if (leilao != null) {
                itemLeilao.setLeilao(leilao);
            } else {
                throw new IllegalArgumentException("Leilão com ID " + itemLeilaoDTO.getIdLeilao() + " não encontrado.");
            }
    }

}}
