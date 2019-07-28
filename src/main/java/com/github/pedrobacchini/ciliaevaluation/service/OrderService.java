package com.github.pedrobacchini.ciliaevaluation.service;

import com.github.pedrobacchini.ciliaevaluation.dto.OrderDTO;
import com.github.pedrobacchini.ciliaevaluation.entity.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    List<Order> getAllOrders();

    Order getOrderById(UUID uuid);

    Order createOrder(OrderDTO order);
}
