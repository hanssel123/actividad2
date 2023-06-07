package com.unir.orders.service;

import com.unir.orders.model.pojo.Product;
import com.unir.orders.model.request.UpdateInventoryRequest;

public interface InventoryService {
	
	Product updateQuantityInventory(UpdateInventoryRequest request);

}
