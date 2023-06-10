package com.unir.orders.model.pojo;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Item {
    @Id
    private Long id;

    @Column(name = "currency")
    private String currency;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private int quantity;

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems;
}
