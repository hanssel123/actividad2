package com.unir.orders.controller;

import com.unir.orders.model.response.Logistic;
import com.unir.orders.service.LogisticService;
import com.unir.orders.service.LogisticServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("logistic")
@RequiredArgsConstructor
@Slf4j
public class LogisticController {
    private final LogisticServiceImpl service;

    @GetMapping
    public ResponseEntity<List<Logistic>> getLogistics() {
        List<Logistic> logistics = service.getLogistics();

        if (logistics != null) {
            return ResponseEntity.ok(logistics);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
