package com.github.pedrobacchini.ciliaevaluation.repository;

import com.github.pedrobacchini.ciliaevaluation.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends UuidRepository<Order> {
}
