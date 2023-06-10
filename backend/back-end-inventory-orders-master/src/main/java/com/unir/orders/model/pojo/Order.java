package com.unir.orders.model.pojo;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "currency")
    private String currency;

    @ManyToOne
    @JoinColumn(name = "idCustomer")
    private Customer customer;

    @Column(name = "number")
    private String number;

    @Column(name = "paymentMethod")
    private String paymentMethod;

    @Column(name = "status")
    private String status;

    @Column(name = "totalAmount")
    private Double totalAmount;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;
}
