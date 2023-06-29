package com.unir.orders.data;

import com.unir.orders.model.pojo.OrderProduct;
import com.unir.orders.model.pojo.OrderProductId;
import com.unir.orders.model.response.reports.ProductAmountItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductId> {
    @Query("SELECT new com.unir.orders.model.response.reports.ProductAmountItem(SUM(p.price), p.name, p.category) FROM OrderProduct op JOIN op.product p JOIN op.order o WHERE o.createdAt <= :maxDate GROUP BY p.name")
    List<ProductAmountItem> reportProductsAmountUntilDate(@Param("maxDate") Date maxDate);
}
