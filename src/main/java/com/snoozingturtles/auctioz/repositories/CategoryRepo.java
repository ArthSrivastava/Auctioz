package com.snoozingturtles.auctioz.repositories;

import com.snoozingturtles.auctioz.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface CategoryRepo extends MongoRepository<Category, String> {
}
