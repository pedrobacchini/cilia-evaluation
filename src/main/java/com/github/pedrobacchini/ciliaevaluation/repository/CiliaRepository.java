package com.github.pedrobacchini.ciliaevaluation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
interface CiliaRepository<T> extends JpaRepository<T, UUID> {
}
