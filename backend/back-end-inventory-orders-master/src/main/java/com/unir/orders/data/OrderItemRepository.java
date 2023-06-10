package com.unir.orders.data;

import com.unir.orders.model.pojo.OrderItem;
import com.unir.orders.model.pojo.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {
}
