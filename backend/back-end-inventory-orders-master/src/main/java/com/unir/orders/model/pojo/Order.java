package com.unir.orders.model.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private long id;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "currency")
    private String currency;

    @ManyToOne
    @JoinColumn(name = "idCustomer")
    @JsonBackReference
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
    @JsonManagedReference
    private List<OrderProduct> orderProducts;
}
