package com.github.pedrobacchini.ciliaevaluation.resource;

import com.github.pedrobacchini.ciliaevaluation.dto.OrderDTO;
import com.github.pedrobacchini.ciliaevaluation.entity.Order;
import com.github.pedrobacchini.ciliaevaluation.event.ResourceCreatedEvent;
import com.github.pedrobacchini.ciliaevaluation.service.OrderService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/order")
public class OrderResource {

    private final OrderService orderService;
    private final ApplicationEventPublisher publisher;

    public OrderResource(OrderService orderService,
                         ApplicationEventPublisher publisher) {
        this.orderService = orderService;
        this.publisher = publisher;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Order> getAllOrders() { return orderService.getAllOrders(); }

    @GetMapping(path = "/{uuid}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Order> getOrderById(@PathVariable("uuid") String uuid) {
        Order order = orderService.getOrderById(UUID.fromString(uuid));
        return ResponseEntity.ok(order);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Order> createOrder(@RequestBody @Valid OrderDTO orderDTO, HttpServletResponse response) {
        Order createdOrder = orderService.createOrder(orderDTO);
        publisher.publishEvent(new ResourceCreatedEvent(this, response, createdOrder.getUuid()));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }
}
