package com.unir.products.data;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import com.unir.products.model.pojo.ElasticProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ElasticsearchQuery {
    @Autowired
    private ElasticsearchClient elasticsearchClient;

    private final String indexName = "products";

    public String createOrUpdateProduct(ElasticProduct product) throws IOException {
        IndexResponse response = elasticsearchClient.index(i -> i
                .index(indexName)
                .id(product.getId())
                .document(product)
        );

        /*
        if (response.result().name().equals("Created")) {
            return new StringBuilder("Producto creado satisfactoriamente").toString();
        } else if (response.result().name().equals("Updated")) {
            return new StringBuilder("Producto actualizado correctamente").toString();
        }
         */

        return response.result().name();
    }

    public ElasticProduct getProductById(String productId) throws IOException {
        ElasticProduct product = null;

        GetResponse<ElasticProduct> response = elasticsearchClient.get(g -> g
                        .index(indexName)
                        .id(productId),
                ElasticProduct.class);

        if (response.found()) {
            product = response.source();
        }

        return product;
    }

    public String deleteProductById(String productId) throws IOException {
        DeleteRequest request = DeleteRequest.of(d -> d
                .index(indexName).id(productId));

        DeleteResponse response = elasticsearchClient.delete(request);

        /*
        if (Objects.nonNull(response.result())
                && !response.result().name().equals("NotFound")) {
            return new StringBuilder("Producto eliminado").toString();
        } else {
            return new StringBuilder("El producto no existe").toString();
        }
         */

        return response.result().name();
    }

    public List<ElasticProduct> searchAllProducts() throws IOException {
        List<ElasticProduct> products = null;

        SearchRequest request = SearchRequest.of(s -> s
                .index(indexName));

        SearchResponse<ElasticProduct> response = elasticsearchClient.search(request, ElasticProduct.class);

        if (response.hits().hits().size() > 0) {
            products = new ArrayList<>();
            for (Hit<ElasticProduct> hit : response.hits().hits()) {
                products.add(hit.source());
            }
        }

        return products;
    }

    public ElasticProduct findByName(String name) throws IOException {
        SearchResponse<ElasticProduct> response = elasticsearchClient.search(s -> s
                        .index(indexName)
                        .query(q -> q
                                .term(t -> t
                                        .field("name")
                                        .value(v -> v.stringValue(name))
                                )),
                ElasticProduct.class);

        List<ElasticProduct> products = new ArrayList<>();

        for (Hit<ElasticProduct> hit : response.hits().hits()) {
            products.add(hit.source());
        }

        if (products.size() > 0) {
            return products.get(0);
        } else {
            return null;
        }
    }
}
