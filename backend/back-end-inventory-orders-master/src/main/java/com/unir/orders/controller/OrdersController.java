package com.unir.orders.controller;

import com.unir.orders.model.pojo.Order;
import com.unir.orders.model.request.UpdateStatusRequest;
import com.unir.orders.model.response.ReviewOrder;
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
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{idOrder}")
    public ResponseEntity<Order> getOrder(@PathVariable long idOrder) {
        Order order = service.getOrder(idOrder);

        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
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

    @PatchMapping("/{idOrder}/status")
    public ResponseEntity<Order> updateStatus(@PathVariable long idOrder, @RequestBody UpdateStatusRequest request) {
        Order updatedOrder = service.updateOrderStatus(idOrder, request);

        if (updatedOrder != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedOrder);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    /*
        Review de clientes
     */
    @GetMapping("/review")
    public ResponseEntity<List<ReviewOrder>> getReviewOrders() {
        List<ReviewOrder> reviewOrders = service.getReviewOrders();

        if (reviewOrders != null) {
            return ResponseEntity.ok(reviewOrders);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
