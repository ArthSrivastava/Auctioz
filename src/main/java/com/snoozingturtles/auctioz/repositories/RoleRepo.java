package com.snoozingturtles.auctioz.repositories;

import com.snoozingturtles.auctioz.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepo extends MongoRepository<Role, String> {
    Optional<Role> findByName(String name);
}
