package com.anderson.carrinho.model;

import com.anderson.carrinho.enumeration.FormaPagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private Cliente cliente;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> itens;
    private Double valorTotal;

    @Enumerated
    private FormaPagamento formaPagamento;
    private boolean fechada;
}
