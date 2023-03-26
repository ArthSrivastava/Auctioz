package com.snoozingturtles.auctioz.repositories;

import com.snoozingturtles.auctioz.models.Bidding;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BiddingRepo extends MongoRepository<Bidding, String> {
    Optional<Bidding> findByProductId(String productId);
}
