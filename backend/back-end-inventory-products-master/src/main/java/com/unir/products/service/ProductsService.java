package com.unir.products.service;

import java.util.Date;
import java.util.List;

import com.unir.products.model.pojo.Product;
import com.unir.products.model.request.CreateProductRequest;
import com.unir.products.model.request.UpdateProductRequest;
import com.unir.products.model.request.UpdateProductStockRequest;

public interface ProductsService {

    List<Product> getProducts();

    Product getProduct(long productId);

    Boolean removeProduct(long productId);

    Product createProduct(CreateProductRequest request);

    Product updateProduct(long productId, UpdateProductRequest request);

    Product updateProductStock(long productId, UpdateProductStockRequest request);

    /**
     * Consultas adicionales
     */
    List<Product> getByNameStartsWith(String prefix);

    List<Product> getByStockAvailable(boolean isAvailable);

    List<Product> filterBetweenCreatedDates(Date dateStart, Date dateEnd);

    List<Product> getByCategory(String category);

    List<Product> getByPriceGreaterThan(Double price);

    List<Product> getByPriceLessThan(Double price);
}
