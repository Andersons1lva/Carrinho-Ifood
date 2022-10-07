package com.anderson.carrinho.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
@Data
public class ItemDto {
    private Long produtoId;
    private int quantidade;
    private Long carrinhoId;
}
