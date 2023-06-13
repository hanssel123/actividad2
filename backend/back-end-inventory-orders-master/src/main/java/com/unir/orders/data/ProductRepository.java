package com.unir.orders.data;

import com.unir.orders.model.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
