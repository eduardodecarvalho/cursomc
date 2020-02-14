package com.eduardo.cursomc.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduardo.cursomc.domain.Payment;

@Repository
public interface PagamentoRepository extends JpaRepository<Payment, Integer> {

}
