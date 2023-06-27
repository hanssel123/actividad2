package com.unir.orders.service;

import com.unir.orders.model.response.ReviewOrder;

import java.util.List;

public interface ReviewService {
    List<ReviewOrder> getReviewOrders();
}
