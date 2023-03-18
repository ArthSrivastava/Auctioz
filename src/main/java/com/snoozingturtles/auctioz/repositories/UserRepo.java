package com.snoozingturtles.auctioz.repositories;

import com.snoozingturtles.auctioz.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {

}
