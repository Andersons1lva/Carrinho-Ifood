package com.anderson.carrinho.repository;

import com.anderson.carrinho.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Itemrepository extends JpaRepository<Item, Long> {
}
