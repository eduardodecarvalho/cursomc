package com.eduardo.cursomc.domain.repositories;

import com.eduardo.cursomc.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Client, Integer> {

    @Transactional(readOnly = true)
    Optional<Client> findByEmail(String email);

    @Transactional(readOnly = true)
    Optional<Client> findByIsNotIdAndEmail(Integer id, String email);
}
