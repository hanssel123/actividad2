package com.unir.orders.service;

import com.unir.orders.model.pojo.Order;
import com.unir.orders.model.request.CreateOrderRequest;

import java.util.List;

public interface OrdersService {
    List<Order> getOrders();

    Order createOrder(CreateOrderRequest request);

}
