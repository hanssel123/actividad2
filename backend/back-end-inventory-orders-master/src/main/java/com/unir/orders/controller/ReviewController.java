package com.unir.orders.controller;

import com.unir.orders.model.response.Logistic;
import com.unir.orders.model.response.ReviewOrder;
import com.unir.orders.service.LogisticServiceImpl;
import com.unir.orders.service.ReviewService;
import com.unir.orders.service.ReviewServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("review")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {
    private final ReviewServiceImpl service;

    @GetMapping("/orders")
    public ResponseEntity<List<ReviewOrder>> getReviewOrders() {
        List<ReviewOrder> reviewOrders = service.getReviewOrders();

        if (reviewOrders != null) {
            return ResponseEntity.ok(reviewOrders);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
