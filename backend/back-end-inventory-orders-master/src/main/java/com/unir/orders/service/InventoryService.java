package com.unir.orders.service;

import com.unir.orders.model.pojo.Product;
import com.unir.orders.model.request.UpdateInventoryRequest;
import com.unir.orders.model.response.ProductResponse;

public interface InventoryService {

    ProductResponse updateQuantityInventory(long id, UpdateInventoryRequest request);

}
