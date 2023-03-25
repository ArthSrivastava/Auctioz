package com.snoozingturtles.auctioz.services;

import com.snoozingturtles.auctioz.dto.ProductDto;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);
    void updateProduct(ProductDto productDto);
    ProductDto getProductById(String productId, String sellerId);
    ProductDto getAllProductsByUserId(String sellerId);
    void deleteProduct(String productId, String sellerId);
}
