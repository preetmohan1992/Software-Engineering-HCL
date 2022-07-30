package com.glovoapp.backender.http.controllers;

import com.glovoapp.backender.http.handlers.ResourceNotFoundException;
import com.glovoapp.backender.http.requests.OrderRequest;
import com.glovoapp.backender.http.responses.OrderResponse;
import com.glovoapp.backender.models.Order;
import com.glovoapp.backender.repositories.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    OrderResponse getById(@PathVariable int id) {
        Optional<Order> order = orderRepository.findById(id);

        if (!order.isPresent()) {
            throw new ResourceNotFoundException("Order not found");
        }

        return OrderResponse.fromOrder(order.get());
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<OrderResponse> create(@Valid @RequestBody OrderRequest orderRequest) {
        Order order = orderRepository.save(orderRequest.toOrder());

        return new ResponseEntity<>(OrderResponse.fromOrder(order), HttpStatus.CREATED);
    }
}
