package com.github.pedrobacchini.ciliaevaluation.service.impl;

import com.github.pedrobacchini.ciliaevaluation.config.LocaleMessageSource;
import com.github.pedrobacchini.ciliaevaluation.dto.OrderDTO;
import com.github.pedrobacchini.ciliaevaluation.entity.Order;
import com.github.pedrobacchini.ciliaevaluation.entity.OrderItem;
import com.github.pedrobacchini.ciliaevaluation.exception.ObjectNotFoundException;
import com.github.pedrobacchini.ciliaevaluation.repository.OrderItemRepository;
import com.github.pedrobacchini.ciliaevaluation.repository.OrderRepository;
import com.github.pedrobacchini.ciliaevaluation.service.ClientService;
import com.github.pedrobacchini.ciliaevaluation.service.OrderService;
import com.github.pedrobacchini.ciliaevaluation.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ClientService clientService;
    private final ProductService productService;
    private final LocaleMessageSource localeMessageSource;

    OrderServiceImpl(OrderRepository orderRepository,
                     OrderItemRepository orderItemRepository,
                     ClientService clientService,
                     ProductService productService,
                     LocaleMessageSource localeMessageSource) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.clientService = clientService;
        this.productService = productService;
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

    @Override
    @Transactional
    public Order createOrder(OrderDTO orderDTO) {
        Order order = new Order(clientService.getClientById(orderDTO.getClient().getUuid()));
        order = orderRepository.save(order);
        final Order finalOrder = order;
        List<OrderItem> orderItems = orderDTO.getItens().stream().map(
                orderItemDTO -> new OrderItem(finalOrder,
                        productService.getProductById(orderItemDTO.getProduct().getUuid()),
                        orderItemDTO.getQuantity()))
                .collect(Collectors.toList());
        order.getItens().addAll(orderItems);
        orderItemRepository.saveAll(order.getItens());
        return order;
    }
}
