package com.eduardo.cursomc.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduardo.cursomc.domain.OrderedItem;

@Repository
public interface ItemPedidoRepository extends JpaRepository<OrderedItem, Integer> {

}
