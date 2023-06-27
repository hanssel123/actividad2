package com.unir.products.service;

import com.unir.products.model.pojo.ElasticProduct;
import com.unir.products.model.request.CreateProductRequest;
import com.unir.products.model.request.UpdateProductRequest;
import com.unir.products.model.response.ElasticProductResponse;

import java.io.IOException;
import java.util.List;

public interface ElasticProductsService {
    String createProduct(CreateProductRequest request) throws IOException;

    ElasticProductResponse getProductById(String productId) throws IOException;

    String updateProduct(UpdateProductRequest request) throws IOException;

    List<ElasticProductResponse> searchAllProducts() throws IOException;

    ElasticProductResponse findByName(String name) throws IOException;

    String deleteProduct(String productId) throws IOException;

}
