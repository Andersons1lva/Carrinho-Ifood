package com.anderson.carrinho.model;

import javax.persistence.*;
import java.util.List;

public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nome;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Produto> cardapio;

    @Enumerated
    private Endereco endereco;
}
