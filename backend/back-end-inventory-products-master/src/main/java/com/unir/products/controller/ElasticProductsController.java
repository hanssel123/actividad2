package com.unir.products.controller;

import com.unir.products.model.pojo.ElasticProduct;
import com.unir.products.model.request.CreateProductRequest;
import com.unir.products.model.request.UpdateProductRequest;
import com.unir.products.model.response.ElasticProductResponse;
import com.unir.products.service.ElasticProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("elastic/product")
@RequiredArgsConstructor
@Slf4j
public class ElasticProductsController {

    private final ElasticProductsService service;

    /*
        Operaciones CRUD básicas
     */
    @GetMapping
    public ResponseEntity<List<ElasticProductResponse>> searchAllProducts() {
        List<ElasticProductResponse> products = null;

        try {
            products = service.searchAllProducts();

            if (products != null) {
                return ResponseEntity.ok(products);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ElasticProductResponse> getProductById(@PathVariable String productId) {
        ElasticProductResponse product = null;
        try {
            product = service.getProductById(productId);

            if (product != null) {
                return ResponseEntity.ok(product);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody CreateProductRequest request) {
        try {
            String result = service.createProduct(request);

            if (result.equals("Created")) {
                return ResponseEntity.status(HttpStatus.CREATED).body(result);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable String productId, @RequestBody UpdateProductRequest request) {
        try {
            String result = service.updateProduct(request);

            if (result.equals("Updated")) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable String productId) {
        try {
            String result = service.deleteProduct(productId);

            if (!result.equals("NotFound")) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /*
        Consultas avanzadas
     */
    @GetMapping("name/{name}")
    public ResponseEntity<ElasticProductResponse> getProductByName(@PathVariable String name) {
        try {
            ElasticProductResponse product = service.findByName(name);

            if (product != null) {
                return ResponseEntity.ok(product);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /*
    @GetMapping("/available/{isAvailable}")
    public ResponseEntity<List<ElasticProduct>> getProductsByStockAvailable(@RequestHeader Map<String, String> headers, @PathVariable boolean isAvailable) {

        log.info("headers {}", headers);
        List<ElasticProduct> products = service.getByAvailability(isAvailable);

        if (products != null) {
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/created-at-between/{dateStart}/{dateEnd}")
    public ResponseEntity<List<ElasticProduct>> getProductsFilterBetweenCreatedDates(
            @RequestHeader Map<String, String> headers,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateStart,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateEnd) {

        log.info("headers {}", headers);
        List<ElasticProduct> products = service.filterBetweenCreatedDates(dateStart, dateEnd);

        if (products != null) {
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ElasticProduct>> getProductsByCategory(@RequestHeader Map<String, String> headers, @PathVariable String category) {

        log.info("headers {}", headers);
        List<ElasticProduct> products = service.getByCategory(category);

        if (products != null) {
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/price/{price}/greater")
    public ResponseEntity<List<ElasticProduct>> getProductsByPriceGreaterThan(@RequestHeader Map<String, String> headers, @PathVariable Double price) {

        log.info("headers {}", headers);
        List<ElasticProduct> products = service.getByPriceGreaterThan(price);

        if (products != null) {
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/price/{price}/less")
    public ResponseEntity<List<ElasticProduct>> getProductsByPriceLessThan(@RequestHeader Map<String, String> headers, @PathVariable Double price) {

        log.info("headers {}", headers);
        List<ElasticProduct> products = service.getByPriceLessThan(price);

        if (products != null) {
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

     */

}
