package com.anderson.carrinho.service.impl;

import com.anderson.carrinho.enumeration.FormaPagamento;
import com.anderson.carrinho.model.Carrinho;
import com.anderson.carrinho.model.Item;
import com.anderson.carrinho.model.Restaurante;
import com.anderson.carrinho.repository.CarrinhoRepository;
import com.anderson.carrinho.repository.ItemRepository;
import com.anderson.carrinho.repository.ProdutoRepository;
import com.anderson.carrinho.resource.dto.ItemDto;
import com.anderson.carrinho.service.CarrinhoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarrinhoServiceImpl implements CarrinhoService {
    private final CarrinhoRepository carrinhoRepository;
    private final ProdutoRepository produtoRepository;

    private final ItemRepository itemRepository;

    @Override
    public Item incluirItemNoCarrinho(ItemDto itemDto) {

        Carrinho carrinho = verCarrinho(itemDto.getCarrinhoId());

        if (carrinho.isFechada()) {
            throw new RuntimeException("O carrinho está fechado");
        }

        Item itemParaSerInserido = Item.builder()
                .quantidade(itemDto.getQuantidade())
                .carrinho(carrinho)
                .produto(produtoRepository.findById(itemDto.getProdutoId()).orElseThrow(
                        () -> {
                            throw new RuntimeException("Esse Produto não existe");
                        }
                ))
                .build();

        List<Item> itensDoCarrinho = carrinho.getItens();
        if (itensDoCarrinho.isEmpty()) {
            itensDoCarrinho.add(itemParaSerInserido);
        } else {
            Restaurante restauranteAtual = itensDoCarrinho.get(0).getProduto().getRestaurante();
            Restaurante restauranteDoItemParaAdicionar = itemParaSerInserido.getProduto().getRestaurante();
            if (restauranteAtual.equals(restauranteDoItemParaAdicionar)) {
                itensDoCarrinho.add(itemParaSerInserido);
            } else {
                throw new RuntimeException("Não é possivel adicionar produtos de Restaurantes Diferentes");
            }
        }

        //Multiplicação do valor dos Produtos
        List<Double> valorDosItens = new ArrayList<>();
        for (Item itemDoCarrinho : itensDoCarrinho) {
            double valorTotalItem = itemDoCarrinho.getProduto().getValorUnitario() * itemDoCarrinho.getQuantidade();
            valorDosItens.add(valorTotalItem);
        }

        double valorTotalCarrinho = valorDosItens.stream()
                .mapToDouble(valorTotalDeCadaItem -> valorTotalDeCadaItem)
                .sum();
        carrinho.setValorTotal(valorTotalCarrinho);
        carrinhoRepository.save(carrinho);
        return itemParaSerInserido;
    }

    @Override
    public Carrinho verCarrinho(Long id) {
        return carrinhoRepository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException("Esse Carrinho não existe");
                }
        );
    }

    @Override
    public Carrinho fecharCarrinho(Long id, int numeroformaPagamento) {
        Carrinho carrinho = verCarrinho(id);

        //verifica se o carrinho está vazio
        if (carrinho.getItens().isEmpty()) {
            throw new RuntimeException("Inclua itens no carrinho!");
        }

        FormaPagamento formaPagamento = numeroformaPagamento == 0 ? FormaPagamento.DINHEIRO : FormaPagamento.MAQUINHA;
        carrinho.setFormaPagamento(formaPagamento);
        carrinho.setFechada(true);
        return carrinhoRepository.save(carrinho);
    }
}
