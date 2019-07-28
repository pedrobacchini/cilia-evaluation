package com.github.pedrobacchini.ciliaevaluation.service.impl;

import com.github.pedrobacchini.ciliaevaluation.entity.Order;
import com.github.pedrobacchini.ciliaevaluation.repository.OrderRepository;
import com.github.pedrobacchini.ciliaevaluation.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
final class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    OrderServiceImpl(OrderRepository orderRepository) { this.orderRepository = orderRepository; }

    @Override
    public List<Order> getAllOrders() { return orderRepository.findAll(); }
}
