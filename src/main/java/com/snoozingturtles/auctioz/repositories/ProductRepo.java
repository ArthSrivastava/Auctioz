package com.snoozingturtles.auctioz.repositories;

import com.snoozingturtles.auctioz.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends MongoRepository<Product, String> {
    Optional<Product> findByIdAndSellerId(String productId, String sellerId);
    List<Product> findAllBySellerId(String sellerId);
    List<Product> findAllByCategoryId(String categoryId);
}
