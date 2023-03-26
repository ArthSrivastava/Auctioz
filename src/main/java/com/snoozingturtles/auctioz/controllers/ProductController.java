package com.snoozingturtles.auctioz.controllers;

import com.snoozingturtles.auctioz.dto.ProductDto;
import com.snoozingturtles.auctioz.payloads.ApiResponse;
import com.snoozingturtles.auctioz.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sellers")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping("/{sellerId}/products")
    public ResponseEntity<ApiResponse> createProduct(@PathVariable String sellerId,
                                                     @RequestBody ProductDto productDto) {
        ProductDto product = productService.createProduct(sellerId, productDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{productId}")
                .buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(
                ApiResponse.builder()
                        .message("Product listed successfully!")
                        .success(true)
                        .build()
        );
    }
    @GetMapping("/{sellerId}/products")
    public ResponseEntity<List<ProductDto>> getAllProductsBySellerId(@PathVariable String sellerId) {
        return ResponseEntity.ok(productService.getAllProductsBySellerId(sellerId));
    }

    @GetMapping("/{sellerId}/products/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable String sellerId,
                                                     @PathVariable String productId) {
        return ResponseEntity.ok(productService.getProductById(productId, sellerId));
    }

    @PutMapping("/{sellerId}/products/{productId}")
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
