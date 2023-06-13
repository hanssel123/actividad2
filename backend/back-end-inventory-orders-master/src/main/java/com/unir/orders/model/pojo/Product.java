package com.unir.orders.model.pojo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Product {
    @Id
    private long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "currency")
    private String currency;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private Double price;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<OrderProduct> orderProducts;
}
