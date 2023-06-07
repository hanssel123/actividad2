package com.unir.products.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.unir.products.model.request.UpdateProductRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.unir.products.model.pojo.Product;
import com.unir.products.model.request.CreateProductRequest;
import com.unir.products.service.ProductsService;
import com.unir.products.service.ProductsServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
// Crea un constructor con un argumento con rest controller es como poner autowired
@RequiredArgsConstructor
// Para poner logs
@Slf4j
public class ProductsController {

	// con el RestController y required ya se hace la inyeccion pero a nivel de constructor
	// inyeccion por interfaz
    //	private final ProductsService service;
	private final ProductsServiceImpl service;
	
	//Peticion Get /Products
	@GetMapping("/product")
	public ResponseEntity<List<Product>> getProductsList(@RequestHeader Map<String, String> headers) {
	// Si se desea trabajar con params /products?name_x=cepillo&precio=12
	// public ResponseEntity<List<Product>> getProducts(@RequestHeader Map<String, String> headers, 
	// @RequestParam(name = "name_x", required = true) String name, @RequestParam Integer precio ){ name =...
		log.info("headers: {}", headers);
		List<Product> products = service.getProducts();

		if (products != null) {
			return ResponseEntity.ok(products);
		} else {
			return ResponseEntity.ok(Collections.emptyList());
		}
	}

	@GetMapping("/product/{productId}")
	// se podría cambiar a ResponseEntity<Object> 
	public ResponseEntity<Product> getProduct(@PathVariable String productId) {

		log.info("Request received for product {}", productId);
		Product product = service.getProduct(productId);

		if (product != null) {
			return ResponseEntity.ok(product);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/product/{productId}")
	public ResponseEntity<Void> deleteProduct(@PathVariable String productId) {

		Boolean removed = service.removeProduct(productId);

		if (Boolean.TRUE.equals(removed)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping("/product")
	public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest request) {

		Product createdProduct = service.createProduct(request);

		if (createdProduct != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
		} else {
			return ResponseEntity.badRequest().build();
		}

	}

	@PutMapping("/product")
	public ResponseEntity<Product> updateProduct(@RequestBody UpdateProductRequest request) {

		Product updatedProduct = service.updateProduct(request);

		if (updatedProduct != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedProduct);
		} else {
			return ResponseEntity.badRequest().build();
		}

	}

}
