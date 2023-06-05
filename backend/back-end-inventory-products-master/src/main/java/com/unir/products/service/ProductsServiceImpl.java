package com.unir.products.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.unir.products.data.ProductRepository;
import com.unir.products.model.pojo.Product;
import com.unir.products.model.request.CreateProductRequest;

@Service
public class ProductsServiceImpl implements ProductsService {

	@Autowired
	private ProductRepository repository;

	@Override
	public List<Product> getProducts() {

		List<Product> products = repository.findAll();
		return products.isEmpty() ? null : products;
	}

	@Override
	public Product getProduct(String productId) {
		return repository.findById(Long.valueOf(productId)).orElse(null);
	}

	@Override
	public Boolean removeProduct(String productId) {

		Product product = repository.findById(Long.valueOf(productId)).orElse(null);

		if (product != null) {
			repository.delete(product);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	@Override
	public Product createProduct(CreateProductRequest request) {

//		if (request != null && StringUtils.hasLength(request.getName().trim())
//				&& StringUtils.hasLength(request..trim())
//				&& StringUtils.hasLength(request.getCountry().trim()) && request.getVisible() != null) {
		
		if (request != null && StringUtils.hasLength(request.getName().trim())
				&& StringUtils.hasLength(request.getCategory().trim())
				&& request.getCreatedAt() != null
				&& StringUtils.hasLength(request.getCurrency().trim())
				&& StringUtils.hasLength(request.getImage().trim())
				&& request.getInStock() != null
				&& request.getIsAvailable() != null
				&& request.getIsShippable() != null
				&& request.getPrice() > 0
				&& request.getQuantity() > 0
				&& StringUtils.hasLength(request.getSku().trim())
				&& StringUtils.hasLength(request.getStatus().trim())
				&& request.getUpdatedAt() != null
				&& request.getVariants() > 0
				) {

//			Product product = Product.builder().name(request.getName()).description(request.getDescription())
//					.country(request.getCountry()).visible(request.getVisible()).build();
			
			Product product = Product.builder().name(request.getName())
					.category(request.getCategory())
					.createdAt(request.getCreatedAt())
					.currency(request.getCurrency())
					.image(request.getImage())
					.inStock(request.getInStock())
					.isAvailable(request.getIsAvailable())
					.isShippable(request.getIsShippable())
					.price(request.getPrice())
					.quantity(request.getQuantity())
					.sku(request.getSku())
					.status(request.getStatus())
					.updatedAt(request.getUpdatedAt())
					.variants(request.getVariants())
					.build();

			return repository.save(product);
		} else {
			return null;
		}
	}

}
