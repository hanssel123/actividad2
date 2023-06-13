package com.unir.orders.model.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private String category;
    private String currency;
    private String image;
    private Double price;
}
