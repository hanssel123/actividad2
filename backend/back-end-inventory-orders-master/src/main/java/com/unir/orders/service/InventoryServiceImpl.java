package com.unir.orders.service;

import com.unir.orders.model.response.ProductResponse;
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
    public ProductResponse updateQuantityInventory(long id, UpdateInventoryRequest request) {
        ProductResponse product = productsFacade.getProduct(id);
        if (product != null) {
            ProductResponse productUpdated = productsFacade.updateProductStock(id, request);
            return productUpdated;
        } else {
            return null;
        }
    }

}
