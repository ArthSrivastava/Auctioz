package com.snoozingturtles.auctioz.repositories;

import com.snoozingturtles.auctioz.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepo extends MongoRepository<Product, String> {
}
