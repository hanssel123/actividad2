package com.unir.orders.controller;

import com.unir.orders.model.pojo.Order;
import com.unir.orders.service.OrdersServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.unir.orders.model.request.CreateOrderRequest;
import com.unir.orders.service.OrdersService;

import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
@Slf4j
public class OrdersController {

    private final OrdersServiceImpl service;

    @GetMapping
    public ResponseEntity<List<Order>> getOrders(@RequestHeader Map<String, String> headers) {
        log.info("headers: {}", headers);
        List<Order> orders = service.getOrders();

        if (orders != null) {
            return ResponseEntity.ok(orders);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest request) {

        Order createdOrder = service.createOrder(request);

        if (createdOrder != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

}
