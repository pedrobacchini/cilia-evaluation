package com.github.pedrobacchini.ciliaevaluation.repository;

import com.github.pedrobacchini.ciliaevaluation.entity.Client;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends CustomRepository<Client> {

    Optional<Client> findByEmail(String name);
}
