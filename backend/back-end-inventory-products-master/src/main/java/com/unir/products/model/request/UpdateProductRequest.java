package com.unir.products.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequest {

//	private String name;
//	private String country;
//	private String description;
//	private Boolean visible;
	
   // private List<String> attributes;
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
