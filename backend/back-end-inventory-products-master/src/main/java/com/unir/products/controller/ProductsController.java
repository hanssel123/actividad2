package com.unir.products.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.unir.products.model.request.UpdateProductRequest;
import com.unir.products.model.request.UpdateProductStockRequest;
import org.springframework.format.annotation.DateTimeFormat;
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
@RequestMapping("product")
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
    @GetMapping
    public ResponseEntity<List<Product>> getProducts(@RequestHeader Map<String, String> headers) {
        // Si se desea trabajar con params /products?name_x=cepillo&precio=12
        // public ResponseEntity<List<Product>> getProducts(@RequestHeader Map<String, String> headers,
        // @RequestParam(name = "name_x", required = true) String name, @RequestParam Integer precio ){ name =...
        log.info("headers: {}", headers);
        List<Product> products = service.getProducts();

        if (products != null) {
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{productId}")
    // se podría cambiar a ResponseEntity<Object>
    public ResponseEntity<Product> getProduct(@PathVariable long productId) {

        log.info("Request received for product {}", productId);
        Product product = service.getProduct(productId);

        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long productId) {

        Boolean removed = service.removeProduct(productId);

        if (Boolean.TRUE.equals(removed)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest request) {

        Product createdProduct = service.createProduct(request);

        if (createdProduct != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable long productId, @RequestBody UpdateProductRequest request) {

        Product updatedProduct = service.updateProduct(productId, request);

        if (updatedProduct != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedProduct);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{productId}/stock")
    public ResponseEntity<Product> updateProductStock(@PathVariable long productId, @RequestBody UpdateProductStockRequest request) {

        Product updatedProduct = service.updateProductStock(productId, request);

        if (updatedProduct != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedProduct);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Peticiones Get para consultas adicionales
     */

    // Nombre
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Product>> getProductsByName(@RequestHeader Map<String, String> headers, @PathVariable String name) {

        log.info("headers {}", headers);
        List<Product> products = service.getByNameStartsWith(name);

        if (products != null) {
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/available/{isAvailable}")
    public ResponseEntity<List<Product>> getProductsByStockAvailable(@RequestHeader Map<String, String> headers, @PathVariable boolean isAvailable) {

        log.info("headers {}", headers);
        List<Product> products = service.getByStockAvailable(isAvailable);

        if (products != null) {
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/created-at-between/{dateStart}/{dateEnd}")
    public ResponseEntity<List<Product>> getProductsFilterBetweenCreatedDates(
            @RequestHeader Map<String, String> headers,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateStart,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateEnd) {

        log.info("headers {}", headers);
        List<Product> products = service.filterBetweenCreatedDates(dateStart, dateEnd);

        if (products != null) {
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@RequestHeader Map<String, String> headers, @PathVariable String category) {

        log.info("headers {}", headers);
        List<Product> products = service.getByCategory(category);

        if (products != null) {
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/price/{price}/greater")
    public ResponseEntity<List<Product>> getProductsByPriceGreaterThan(@RequestHeader Map<String, String> headers, @PathVariable Double price) {

        log.info("headers {}", headers);
        List<Product> products = service.getByPriceGreaterThan(price);

        if (products != null) {
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/price/{price}/less")
    public ResponseEntity<List<Product>> getProductsByPriceLessThan(@RequestHeader Map<String, String> headers, @PathVariable Double price) {

        log.info("headers {}", headers);
        List<Product> products = service.getByPriceLessThan(price);

        if (products != null) {
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
