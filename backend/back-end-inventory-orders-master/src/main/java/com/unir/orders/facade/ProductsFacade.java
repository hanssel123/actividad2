package com.unir.orders.facade;

import com.unir.orders.model.pojo.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductsFacade {

  @Value("${getProduct.url}")
  private String getProductUrl;

  private final RestTemplate restTemplate;

  public Product getProduct(long id) {

    try {
      String productUrl = String.format(getProductUrl, id);
      Product productReceived = restTemplate.getForObject(productUrl, Product.class);
      return productReceived;
    } catch (HttpClientErrorException e) {
      log.error("Client Error: {}, Product with ID {}", e.getStatusCode(), id);
      return null;
    }
  }

  public Product updateProduct(Product product) {
    try {
      String productUrl = String.format(getProductUrl, "");
      productUrl = productUrl.substring(1, productUrl.length() - 1);
      restTemplate.put(productUrl, product);
      return product;
    } catch (HttpClientErrorException e) {
      log.error("Client Error: {}, Product with ID {}", e.getStatusCode(), product.getId());
      return null;
    }
  }

}
