package com.unir.orders.controller;

import com.unir.orders.model.pojo.Customer;
import com.unir.orders.service.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("customer")
@RequiredArgsConstructor
@Slf4j
public class CustomersController {
    private final CustomerServiceImpl service;

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers(@RequestHeader Map<String, String> headers) {
        log.info("headers: {}", headers);
        List<Customer> customers = service.getCustomers();

        if (customers != null) {
            return ResponseEntity.ok(customers);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomers(@PathVariable long customerId, @RequestHeader Map<String, String> headers) {
        log.info("headers: {}", headers);
        Customer customers = service.getCustomer(customerId);

        if (customers != null) {
            return ResponseEntity.ok(customers);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable long customerId, @RequestHeader Map<String, String> headers) {
        log.info("headers: {}", headers);
        Boolean removed = service.removeCustomer(customerId);

        if (Boolean.TRUE.equals(removed)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
