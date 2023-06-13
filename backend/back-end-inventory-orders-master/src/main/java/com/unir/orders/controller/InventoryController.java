package com.unir.orders.controller;

import com.unir.orders.model.pojo.Product;
import com.unir.orders.model.response.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.unir.orders.model.request.UpdateInventoryRequest;
import com.unir.orders.service.InventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService service;

    @PatchMapping("/{productId}")
    public ResponseEntity<ProductResponse> UpdateInventory(@PathVariable long productId, @RequestBody UpdateInventoryRequest request) {

        ProductResponse result = service.updateQuantityInventory(productId, request);

        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }
}
