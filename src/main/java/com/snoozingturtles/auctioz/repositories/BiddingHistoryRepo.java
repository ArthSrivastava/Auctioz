package com.snoozingturtles.auctioz.repositories;

import com.snoozingturtles.auctioz.models.BiddingHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BiddingHistoryRepo extends MongoRepository<BiddingHistory, String> {
    Optional<BiddingHistory> findBiddingHistoryByProductIdAndUserIdAndId(String productId, String userId, String bidId);
    List<BiddingHistory> findAllByProductId(String productId);
    List<BiddingHistory> findAllByProductIdAndUserId(String productId, String userId);
    List<BiddingHistory> findAllByUserId(String userId);
}
