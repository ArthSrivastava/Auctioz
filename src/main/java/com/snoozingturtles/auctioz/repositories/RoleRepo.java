package com.snoozingturtles.auctioz.repositories;

import com.snoozingturtles.auctioz.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepo extends MongoRepository<Role, String> {
}
