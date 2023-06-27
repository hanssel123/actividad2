package com.unir.products.model.response;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ElasticProductResponse {
    private long id;
    private String name;
    private String category;
    private Date createdAt;
    private String currency;
    private String image;
    private Boolean inStock;
    private Boolean isAvailable;
    private Boolean isShippable;
    private Double price;
    private int quantity;
    private String sku;
    private String status;
    private Date updatedAt;
    private int variants;
}
