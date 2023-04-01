package com.snoozingturtles.auctioz.repositories;

import com.snoozingturtles.auctioz.models.MyOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MyOrderRepo extends MongoRepository<MyOrder, String> {
    Optional<MyOrder> findByRazorpayOrderId(String razorpayOrderId);
}
