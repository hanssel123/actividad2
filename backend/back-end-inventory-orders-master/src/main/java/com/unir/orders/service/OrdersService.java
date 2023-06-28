package com.unir.orders.service;

import com.unir.orders.model.pojo.Order;
import com.unir.orders.model.request.CreateOrderRequest;
import com.unir.orders.model.request.UpdateStatusRequest;
import com.unir.orders.model.response.OrderResponse;

import java.util.List;

public interface OrdersService {
    List<OrderResponse> getOrders();

    OrderResponse getOrder(long id);

    OrderResponse createOrder(CreateOrderRequest request);

    OrderResponse updateOrderStatus(long idOrder, UpdateStatusRequest request);

}
