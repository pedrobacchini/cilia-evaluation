package com.github.pedrobacchini.ciliaevaluation.service.impl;

import com.github.pedrobacchini.ciliaevaluation.config.LocaleMessageSource;
import com.github.pedrobacchini.ciliaevaluation.entity.Order;
import com.github.pedrobacchini.ciliaevaluation.exception.ObjectNotFoundException;
import com.github.pedrobacchini.ciliaevaluation.repository.OrderRepository;
import com.github.pedrobacchini.ciliaevaluation.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
final class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final LocaleMessageSource localeMessageSource;

    OrderServiceImpl(OrderRepository orderRepository,
                     LocaleMessageSource localeMessageSource) {
        this.orderRepository = orderRepository;
        this.localeMessageSource = localeMessageSource;
    }

    @Override
    public List<Order> getAllOrders() { return orderRepository.findAll(); }

    @Override
    public Order getOrderById(UUID uuid) {
        return orderRepository.findById(uuid)
                .orElseThrow(() -> new ObjectNotFoundException(localeMessageSource
                        .getMessage("object-not-found", uuid, Order.class.getName())));
    }
}
