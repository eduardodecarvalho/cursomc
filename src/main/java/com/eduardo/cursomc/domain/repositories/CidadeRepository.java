package com.eduardo.cursomc.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduardo.cursomc.domain.City;

@Repository
public interface CidadeRepository extends JpaRepository<City, Integer> {

}
