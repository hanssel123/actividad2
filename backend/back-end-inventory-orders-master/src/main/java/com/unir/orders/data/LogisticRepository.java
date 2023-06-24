package com.unir.orders.data;

import com.unir.orders.model.pojo.Order;
import com.unir.orders.model.response.Logistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LogisticRepository extends JpaRepository<Order, Long> {
    @Query("SELECT new com.unir.orders.model.response.Logistic(o.status, COUNT(o)) FROM Order o GROUP BY o.status")
    List<Logistic> getLogistics();
}
