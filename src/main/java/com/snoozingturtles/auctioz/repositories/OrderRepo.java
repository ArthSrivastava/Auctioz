package com.snoozingturtles.auctioz.repositories;

import com.snoozingturtles.auctioz.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepo extends MongoRepository<Order, String> {
}
