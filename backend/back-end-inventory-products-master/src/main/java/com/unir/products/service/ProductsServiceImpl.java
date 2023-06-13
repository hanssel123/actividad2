package com.unir.products.service;

import java.util.Date;
import java.util.List;

import com.unir.products.model.request.UpdateProductRequest;
import com.unir.products.model.request.UpdateProductStockRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.unir.products.data.ProductRepository;
import com.unir.products.model.pojo.Product;
import com.unir.products.model.request.CreateProductRequest;

@Service
// podría poner @Servicie("Service1") se llama con @Qualifier("Service1")
public class ProductsServiceImpl implements ProductsService {

    // Inyección por atributo aqui me contacto con capa de datos
    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> getProducts() {

        List<Product> products = repository.findAll();
        return products.isEmpty() ? null : products;
    }

    @Override
    public Product getProduct(long productId) {
        return repository.findById(productId).orElse(null);
    }

    @Override
    public Boolean removeProduct(long productId) {

        Product product = repository.findById(productId).orElse(null);

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
                    .price(0.0)
                    .quantity(0)
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

    @Override
    public Product updateProduct(long productId, UpdateProductRequest request) {
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
            Product product = Product.builder()
                    .id(productId)
                    .name(request.getName())
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

    @Override
    public Product updateProductStock(long productId, UpdateProductStockRequest request) {
        if (request != null && request.getQuantity() > 0) {
            Product product = repository.findById(productId).orElse(null);

            if (product != null) {
                int newQuantity = product.getQuantity() - request.getQuantity();
                product.setQuantity(newQuantity);
                boolean isAvailable = true;

                if (newQuantity == 0) {
                    isAvailable = false;
                }

                product.setIsAvailable(isAvailable);
                product.setUpdatedAt(new Date());

                return repository.save(product);
            } else {
                return null;
            }

        } else {
            return null;
        }
    }

    @Override
    public List<Product> getByNameStartsWith(String prefix) {
        return repository.findAllByNameStartsWith(prefix);
    }

    @Override
    public List<Product> getByStockAvailable(boolean isAvailable) {
        return repository.findAllByIsAvailable(isAvailable);
    }

    @Override
    public List<Product> filterBetweenCreatedDates(Date dateStart, Date dateEnd) {
        return repository.findByCreatedAtBetween(dateStart, dateEnd);
    }

    @Override
    public List<Product> getByCategory(String category) {
        return repository.findAllByCategory(category);
    }

    @Override
    public List<Product> getByPriceGreaterThan(Double price) {
        return repository.findAllByPriceGreaterThan(price);
    }

    @Override
    public List<Product> getByPriceLessThan(Double price) {
        return repository.findAllByPriceLessThan(price);
    }

}
