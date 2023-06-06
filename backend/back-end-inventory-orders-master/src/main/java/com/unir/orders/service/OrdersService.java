package com.unir.orders.service;

import com.unir.orders.model.request.OrderRequest;

public interface OrdersService {
	
	String createOrder(OrderRequest request);

}
