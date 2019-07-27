package com.github.pedrobacchini.ciliaevaluation.repository;

import com.github.pedrobacchini.ciliaevaluation.entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends UuidRepository<Product> {
}
