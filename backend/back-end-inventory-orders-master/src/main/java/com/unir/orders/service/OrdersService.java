package com.unir.orders.service;

import com.unir.orders.model.pojo.Order;
import com.unir.orders.model.request.CreateOrderRequest;
import com.unir.orders.model.request.UpdateStatusRequest;

import java.util.List;

public interface OrdersService {
    List<Order> getOrders();

    Order getOrder(long id);

    Order createOrder(CreateOrderRequest request);

    Order updateOrderStatus(long idOrder, UpdateStatusRequest request);

}
