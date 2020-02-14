package com.eduardo.cursomc.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduardo.cursomc.domain.Category;

@Repository
public interface CategoriaRepository extends JpaRepository<Category, Integer> {

}
