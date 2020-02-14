package com.eduardo.cursomc.domain.repositories;

import com.eduardo.cursomc.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Transactional(readOnly = true)
    Optional<Cliente> findByEmail(String email);

    @Transactional(readOnly = true)
    Optional<Cliente> findByIsNotIdAndEmail(Integer id, String email);
}
