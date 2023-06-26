package com.unir.products.service;

import com.unir.products.data.ElasticsearchQuery;
import com.unir.products.model.pojo.ElasticProduct;
import com.unir.products.model.request.CreateProductRequest;
import com.unir.products.model.request.UpdateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ElasticProductsServiceImpl implements ElasticProductsService {

    private final ElasticsearchQuery query;

    @Override
    public String createProduct(CreateProductRequest request) throws IOException {
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
                && request.getVariants() > 0) {

            ElasticProduct product = ElasticProduct.builder()
                    .id(String.valueOf(request.getName().hashCode()))
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

            return query.createOrUpdateProduct(product);
        } else {
            return null;
        }
    }

    @Override
    public ElasticProduct getProductById(String productId) throws IOException {
        return query.getProductById(productId);
    }

    @Override
    public String updateProduct(UpdateProductRequest request) throws IOException {
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
                && request.getVariants() > 0) {

            ElasticProduct product = ElasticProduct.builder()
                    .id(String.valueOf(request.getName().hashCode()))
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

            return query.createOrUpdateProduct(product);
        } else {
            return null;
        }
    }

    @Override
    public List<ElasticProduct> searchAllProducts() throws IOException {
        return query.searchAllProducts();
    }

    @Override
    public ElasticProduct findByName(String name) throws IOException {
        return query.findByName(name);
    }

    @Override
    public String deleteProduct(String productId) throws IOException {
        return query.deleteProductById(productId);
    }

}
