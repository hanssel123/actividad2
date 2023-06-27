package com.unir.products.service;

import com.unir.products.data.ElasticsearchQuery;
import com.unir.products.model.pojo.ElasticProduct;
import com.unir.products.model.request.CreateProductRequest;
import com.unir.products.model.request.UpdateProductRequest;
import com.unir.products.model.response.ElasticProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
    public ElasticProductResponse getProductById(String productId) throws IOException {
        ElasticProduct product = query.getProductById(productId);
        ElasticProductResponse response = ElasticProductResponse.builder()
                .id(Long.parseLong(product.getId()))
                .name(product.getName())
                .category(product.getCategory())
                .createdAt(product.getCreatedAt())
                .currency(product.getCurrency())
                .image(product.getImage())
                .inStock(product.getInStock())
                .isAvailable(product.getIsAvailable())
                .isShippable(product.getIsShippable())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .sku(product.getSku())
                .status(product.getStatus())
                .updatedAt(product.getUpdatedAt())
                .variants(product.getVariants())
                .build();

        return response;
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
    public List<ElasticProductResponse> searchAllProducts() throws IOException {
        List<ElasticProduct> products = query.searchAllProducts();

        return products.stream()
                .map(p -> ElasticProductResponse.builder()
                        .id(Long.parseLong(p.getId()))
                        .name(p.getName())
                        .category(p.getCategory())
                        .createdAt(p.getCreatedAt())
                        .currency(p.getCurrency())
                        .image(p.getImage())
                        .inStock(p.getInStock())
                        .isAvailable(p.getIsAvailable())
                        .isShippable(p.getIsShippable())
                        .price(p.getPrice())
                        .quantity(p.getQuantity())
                        .sku(p.getSku())
                        .status(p.getStatus())
                        .updatedAt(p.getUpdatedAt())
                        .variants(p.getVariants())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public ElasticProductResponse findByName(String name) throws IOException {
        ElasticProduct product = query.findByName(name);

        ElasticProductResponse response = ElasticProductResponse.builder()
                .id(Long.parseLong(product.getId()))
                .name(product.getName())
                .category(product.getCategory())
                .createdAt(product.getCreatedAt())
                .currency(product.getCurrency())
                .image(product.getImage())
                .inStock(product.getInStock())
                .isAvailable(product.getIsAvailable())
                .isShippable(product.getIsShippable())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .sku(product.getSku())
                .status(product.getStatus())
                .updatedAt(product.getUpdatedAt())
                .variants(product.getVariants())
                .build();

        return response;
    }

    @Override
    public String deleteProduct(String productId) throws IOException {
        return query.deleteProductById(productId);
    }

}
