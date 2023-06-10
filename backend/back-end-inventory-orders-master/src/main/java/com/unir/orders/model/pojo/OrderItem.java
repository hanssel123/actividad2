package com.unir.orders.model.pojo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderItem {
    @EmbeddedId
    private OrderItemId orderItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idOrder")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idItem")
    private Item item;

    @Column(name = "unitAmount")
    private Double unitAmount;

    @Column(name = "billingCycle")
    private String billingCycle;
}
