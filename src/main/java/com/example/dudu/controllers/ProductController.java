package com.example.dudu.controllers;

import com.example.dudu.dtos.ProductDTO;
import com.example.dudu.dtos.ProductRequestDTO;
import com.example.dudu.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        try {
            List<ProductDTO> products = productService.getAllProducts();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/active")
    public ResponseEntity<List<ProductDTO>> getActiveProducts() {
        try {
            List<ProductDTO> activeProducts = productService.getActiveProducts();
            return ResponseEntity.ok(activeProducts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        try {
            ProductDTO product = productService.getProductById(id);
            return ResponseEntity.ok(product);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductRequestDTO productRequest) {
            ProductDTO createdProduct = productService.createProduct(productRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequestDTO productRequest) {
        try {
            ProductDTO updatedProduct = productService.updateProduct(id, productRequest);
            return ResponseEntity.ok(updatedProduct);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/{id}/toggle-status")
    public ResponseEntity<ProductDTO> toggleProductStatus(@PathVariable Long id) {
        try {
            ProductDTO toggledProduct = productService.toggleProductStatus(id);
            return ResponseEntity.ok(toggledProduct);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
