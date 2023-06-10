package com.unir.orders.data;

import com.unir.orders.model.pojo.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
