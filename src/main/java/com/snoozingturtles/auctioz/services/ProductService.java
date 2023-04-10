package com.snoozingturtles.auctioz.services;

import com.snoozingturtles.auctioz.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(String sellerId, ProductDto productDto);
    void updateProduct(String productId, String sellerId, ProductDto productDto);
    ProductDto getProductById(String productId, String sellerId);
    ProductDto getProductById(String productId);
    List<ProductDto> getAllProductsBySellerId(String sellerId);
    List<ProductDto> getAllProducts();
    List<ProductDto> getAllProductsByCategoryId(String categoryId);
    void deleteProduct(String productId, String sellerId);
}
