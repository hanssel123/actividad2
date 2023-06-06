package com.unir.orders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.unir.orders.facade.ProductsFacade;
import com.unir.orders.model.pojo.Product;
import com.unir.orders.model.request.OrderRequest;

@Service
public class OrdersServiceImpl implements OrdersService {

  @Autowired
  private ProductsFacade productsFacade;

  @Override
  public String createOrder(OrderRequest request) {
    List<Product> products = request.getProducts().stream().map(productsFacade::getProduct).filter(Objects::nonNull).collect(Collectors.toList());
    return products.size() == request.getProducts().size() ? "OK" : "KO";
  }

}
