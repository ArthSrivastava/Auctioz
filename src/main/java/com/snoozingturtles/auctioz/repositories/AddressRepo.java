package com.snoozingturtles.auctioz.repositories;

import com.snoozingturtles.auctioz.models.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepo extends MongoRepository<Address, String> {
    Optional<Address> findByIdAndUserId(String addressId, String userId);
    List<Address> findAllByUserId(String userId);
}
