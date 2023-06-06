package com.unir.orders.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.unir.orders.model.request.OrderRequest;
import com.unir.orders.service.OrdersService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrdersController {

	private final OrdersService service;

	@PostMapping("/orders")
	public ResponseEntity<String> createOrder(@RequestBody OrderRequest request) {

	  String result = service.createOrder(request);
	  
	  if(request != null && "OK".equals(result)) {
	    return ResponseEntity.ok(result);
	  } else {
	    return ResponseEntity.badRequest().build();
	  }
		
	}

}
