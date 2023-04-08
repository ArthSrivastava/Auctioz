package com.snoozingturtles.auctioz.repositories;

import com.snoozingturtles.auctioz.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
