package com.unir.orders.facade;

import com.unir.orders.model.request.UpdateInventoryRequest;
import com.unir.orders.model.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductsFacade {

    @Value("${getProduct.url}")
    private String getProductUrl;

    private final RestTemplate restTemplate;

    public ProductResponse getProduct(long id) {

        try {
            String productUrl = String.format(getProductUrl, id);
            ProductResponse productReceived = restTemplate.getForObject(productUrl, ProductResponse.class);
            return productReceived;
        } catch (HttpClientErrorException e) {
            log.error("Client Error: {}, Product with ID {}", e.getStatusCode(), id);
            return null;
        }
    }

    public ProductResponse updateProductStock(long id, UpdateInventoryRequest request) {
        try {
            String productUrl = String.format(getProductUrl, id) + "/stock";

            /*final URI url = URI.create(productUrl);

            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
            RestTemplate restTemplateForFactory = new RestTemplate(requestFactory);

            ProductResponse responseEntity = restTemplateForFactory.patchForObject(productUrl, request, ProductResponse.class)
            */
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<UpdateInventoryRequest> updateInventoryRequest =
                    new HttpEntity<>(request, headers);

            ResponseEntity<ProductResponse> productResponseEntity =
                    restTemplate.exchange(productUrl, HttpMethod.PATCH, updateInventoryRequest, ProductResponse.class);

            return productResponseEntity.getBody();
        } catch (HttpClientErrorException e) {
            log.error("Client Error: {}, Product with ID {}", e.getStatusCode(), id);
            return null;
        }
    }

}
