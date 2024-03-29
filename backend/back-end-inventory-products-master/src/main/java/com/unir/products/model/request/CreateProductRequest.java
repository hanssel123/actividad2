package com.unir.products.model.request;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {

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
    private String sku;
    private String status;
    private Date updatedAt;
    private int variants;
	
}
