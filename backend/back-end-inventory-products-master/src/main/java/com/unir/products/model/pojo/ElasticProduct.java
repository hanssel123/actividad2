package com.unir.products.model.pojo;

import jakarta.annotation.Nullable;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.*;

import java.util.Date;

@Document(indexName = "products")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ElasticProduct {
    @Id
    private String id;

    @MultiField(mainField = @Field(name = "name", type = FieldType.Keyword),
            otherFields = @InnerField(suffix = "search", type = FieldType.Search_As_You_Type))
    private String name;

    @Field(name = "category", type = FieldType.Keyword)
    private String category;

    @Field(name = "createdAt", type = FieldType.Date)
    private Date createdAt;

    @Field(name = "currency", type = FieldType.Keyword)
    private String currency;

    @Nullable
    @Field(name = "image", type = FieldType.Text)
    private String image;

    @Field(name = "inStock", type = FieldType.Boolean)
    private Boolean inStock;

    @Field(name = "isAvailable", type = FieldType.Boolean)
    private Boolean isAvailable;

    @Field(name = "isShippable", type = FieldType.Boolean)
    private Boolean isShippable;

    @Field(name = "price", type = FieldType.Double)
    private Double price;

    @Field(name = "quantity", type = FieldType.Integer)
    private int quantity;

    @Field(name = "sku", type = FieldType.Text)
    private String sku;

    @Field(name = "status", type = FieldType.Text)
    private String status;

    @Field(name = "updatedAt", type = FieldType.Date)
    private Date updatedAt;

    @Field(name = "variants", type = FieldType.Integer)
    private int variants;
}
