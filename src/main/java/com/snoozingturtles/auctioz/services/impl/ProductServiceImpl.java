package com.snoozingturtles.auctioz.services.impl;

import com.snoozingturtles.auctioz.dto.ProductDto;
import com.snoozingturtles.auctioz.repositories.ProductRepo;
import com.snoozingturtles.auctioz.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    @Override
    public ProductDto createProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public void updateProduct(ProductDto productDto) {
    }

    @Override
    public ProductDto getProductById(String productId, String sellerId) {
        return null;
    }

    @Override
    public ProductDto getAllProductsByUserId(String sellerId) {
        return null;
    }

    @Override
    public void deleteProduct(String productId, String sellerId) {
    }
}
