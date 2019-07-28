package com.github.pedrobacchini.ciliaevaluation.repository;

import com.github.pedrobacchini.ciliaevaluation.entity.OrderItem;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends UuidRepository<OrderItem> {
}
