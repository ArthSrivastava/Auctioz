package com.snoozingturtles.auctioz.controllers;

import com.snoozingturtles.auctioz.dto.ProductDto;
import com.snoozingturtles.auctioz.payloads.ApiResponse;
import com.snoozingturtles.auctioz.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping("/sellers/{sellerId}/products")
    public ResponseEntity<ProductDto> createProduct(@PathVariable String sellerId,
                                                     @Valid @RequestBody ProductDto productDto) {
        ProductDto product = productService.createProduct(sellerId, productDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{productId}")
                .buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(product);
    }   
    @GetMapping("/sellers/{sellerId}/products")
    @PreAuthorize(value = "@userSecurity.hasUserId(authentication, #sellerId)")
    public ResponseEntity<List<ProductDto>> getAllProductsBySellerId(@PathVariable String sellerId) {
        return ResponseEntity.ok(productService.getAllProductsBySellerId(sellerId));
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable String productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> allProducts = productService.getAllProducts();
        return ResponseEntity.ok(allProducts);
    }

    @GetMapping("/categories/{categoryId}/products")
    public ResponseEntity<List<ProductDto>> getAllProductsByCategoryId(@PathVariable String categoryId) {
        List<ProductDto> allProductsByCategoryId = productService.getAllProductsByCategoryId(categoryId);
        return ResponseEntity.ok(allProductsByCategoryId);
    }

    @PutMapping("/sellers/{sellerId}/products/{productId}")
    @PreAuthorize(value = "@userSecurity.hasUserId(authentication, #sellerId)")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable String sellerId,
                                                     @PathVariable String productId,
                                                     @RequestBody ProductDto productDto) {
        productService.updateProduct(productId, sellerId, productDto);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("Product updated successfully!")
                        .success(true)
                        .build()
        );
    }

    @DeleteMapping("/{sellerId}/products/{productId}")
    @PreAuthorize(value = "@userSecurity.hasUserId(authentication, #sellerId)")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable String sellerId,
                                                     @PathVariable String productId) {
        productService.deleteProduct(productId, sellerId);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("Product deleted successfully")
                        .success(true)
                        .build()
        );
    }
}
