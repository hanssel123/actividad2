package com.unir.orders.controller;

import com.unir.orders.model.pojo.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.unir.orders.model.request.UpdateInventoryRequest;
import com.unir.orders.service.InventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InventoryController {

	private final InventoryService service;

	@PatchMapping("/inventory")
	public ResponseEntity<Product> UpdateInventory(@RequestBody UpdateInventoryRequest request) {

	  Product result = service.updateQuantityInventory(request);
	  
	  if(result != null) {
	    return ResponseEntity.ok(result);
	  } else {
	    return ResponseEntity.badRequest().build();
	  }
		
	}
}
