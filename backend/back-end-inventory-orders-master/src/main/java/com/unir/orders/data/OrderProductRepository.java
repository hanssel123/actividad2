package com.unir.orders.data;

import com.unir.orders.model.pojo.OrderProduct;
import com.unir.orders.model.pojo.OrderProductId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductId> {
}
