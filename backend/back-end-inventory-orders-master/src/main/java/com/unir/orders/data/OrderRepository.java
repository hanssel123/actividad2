package com.unir.orders.data;

import com.unir.orders.model.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT SUM(o.totalAmount) FROM Order o WHERE o.createdAt <= :maxDate")
    double totalAmountUntilDate(@Param("maxDate") Date maxDate);
}
