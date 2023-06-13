package com.unir.orders.model.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders_products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderProduct {
    @EmbeddedId
    private OrderProductId orderProductId;

    @ManyToOne
    @MapsId("idOrder")
    @JoinColumn(name = "idOrder")
    @JsonBackReference
    private Order order;

    @ManyToOne
    @MapsId("idProduct")
    @JoinColumn(name = "idProduct")
    @JsonBackReference
    private Product product;

    @Column(name = "quantity")
    private int quantity;
}
