package com.github.pedrobacchini.ciliaevaluation.repository;

import com.github.pedrobacchini.ciliaevaluation.entity.Client;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ClientRepository extends UuidRepository<Client> {

    @Transactional(readOnly = true)
    Optional<Client> findByEmail(String name);
}
