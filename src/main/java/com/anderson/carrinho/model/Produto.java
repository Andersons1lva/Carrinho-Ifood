package com.anderson.carrinho.model;

import com.anderson.carrinho.enumeration.FormaPagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

import javax.persistence.*;
import java.util.List;

public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nome;
    private double valorUnitario;
    @Builder.Default
    private Boolean disponivel = true;
    @ManyToOne
    @JsonIgnore
    private Restaurante restaurante;

}
