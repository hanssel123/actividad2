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

  public Product getProduct(String id) {

    try {
      return restTemplate.getForObject(String.format(getProductUrl, id), Product.class);
    } catch (HttpClientErrorException e) {
      log.error("Client Error: {}, Product with ID {}", e.getStatusCode(), id);
      return null;
    }
  }

}
