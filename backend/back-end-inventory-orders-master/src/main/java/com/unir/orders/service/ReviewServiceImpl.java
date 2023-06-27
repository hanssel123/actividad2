package com.unir.orders.service;

import com.unir.orders.data.ReviewRepository;
import com.unir.orders.model.response.ReviewOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository repository;

    @Override
    public List<ReviewOrder> getReviewOrders() {
        return repository.getReviewOrders();
    }
}
