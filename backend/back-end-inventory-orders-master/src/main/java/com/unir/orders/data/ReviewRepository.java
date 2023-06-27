package com.unir.orders.data;

import com.unir.orders.model.pojo.Order;
import com.unir.orders.model.response.ReviewOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Order, Long> {
    @Query("SELECT new com.unir.orders.model.response.ReviewOrder(o.review, COUNT(o)) FROM Order o GROUP BY o.review")
    List<ReviewOrder> getReviewOrders();
}
