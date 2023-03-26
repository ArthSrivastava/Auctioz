package com.snoozingturtles.auctioz.services;

import com.snoozingturtles.auctioz.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(String sellerId, ProductDto productDto);
    void updateProduct(String productId, String sellerId, ProductDto productDto);
    ProductDto getProductById(String productId, String sellerId);
    List<ProductDto> getAllProductsBySellerId(String sellerId);
    void deleteProduct(String productId, String sellerId);
}
