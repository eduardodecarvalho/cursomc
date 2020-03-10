package com.eduardo.cursomc.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eduardo.cursomc.domain.Client;

@Repository
public interface ClienteRepository extends JpaRepository<Client, Integer> {

    @Transactional(readOnly = true)
    Optional<Client> findByEmail(String email);

    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM client c "
            + "WHERE c.id != :id "
            + "AND c.email = :email; ", nativeQuery = true)
    Optional<Client> findByIsNotIdAndEmail(@Param(value = "id") Integer id, @Param(value = "email") String email);

}
