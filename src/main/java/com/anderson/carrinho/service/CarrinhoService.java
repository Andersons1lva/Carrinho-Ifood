package com.anderson.carrinho.service;

import com.anderson.carrinho.model.Carrinho;
import com.anderson.carrinho.model.Item;
import com.anderson.carrinho.resource.dto.ItemDto;

public interface CarrinhoService {
    Item incluirItemNoCarrinho(ItemDto itemDto);
    Carrinho verCarrinho(Long id);
    Carrinho fecharCarrinho(Long id, int formaPagamento);

}
