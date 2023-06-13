package com.unir.orders.data;

import com.unir.orders.model.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
