package com.unir.orders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unir.orders.facade.ProductsFacade;
import com.unir.orders.model.pojo.Product;
import com.unir.orders.model.request.UpdateInventoryRequest;

@Service
public class InventoryServiceImpl implements InventoryService {

  @Autowired
  private ProductsFacade productsFacade;

  @Override
  public Product updateQuantityInventory(UpdateInventoryRequest request) {
    Product product = productsFacade.getProduct(request.getProductId());
    if (product == null) return null;
    product.setQuantity(request.getQuantity());
    product = productsFacade.updateProduct(product);

    return product;
  }

}
