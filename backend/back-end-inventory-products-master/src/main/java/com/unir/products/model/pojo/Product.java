package com.unir.products.model.pojo;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// Manejara datos
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
//	@Column(name = "name", unique = true)
//	private String name;
//	
//	@Column(name = "country")
//	private String country;
//	
//	@Column(name = "description")
//	private String description;
//	
//	@Column(name = "visible")
//	private Boolean visible;

    // private List<String> attributes;
	
	@Column(name = "name", unique = true)
    private String name;
	
	@Column(name = "category")
    private String category;
	
	@Column(name = "createdA")
    private Date createdAt;
	
	@Column(name = "currency")
    private String currency;
	
	@Column(name = "image")
    private String image;
	
	@Column(name = "inStock")
    private Boolean inStock;
	
	@Column(name = "isAvailable")
    private Boolean isAvailable;
	
	@Column(name = "isShippable")
    private Boolean isShippable;
	
	@Column(name = "price")
    private Double price;
	
	@Column(name = "quantity")
    private int quantity;
	
	@Column(name = "sku")
    private String sku;
	
	@Column(name = "status")
    private String status;
	
	@Column(name = "updatedAt")
    private Date updatedAt;
	
	@Column(name = "variants")
    private int variants;
	
}
