package com.unir.orders.model.pojo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "address1")
    private String address1;

    @Column(name = "address2")
    private String address2;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "customer")
    @JsonManagedReference
    private List<Order> orders;

    @PreRemove
    public void setOrdersNullable() {
        orders.forEach(order -> order.setCustomer(null));
    }
}
