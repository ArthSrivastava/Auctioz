package com.snoozingturtles.auctioz.services;

import com.snoozingturtles.auctioz.dto.BiddingDto;

public interface BiddingService {
    BiddingDto createBidding(String productId, String sellerId, BiddingDto biddingDto);
    void updateBidding(String productId, String sellerId, BiddingDto biddingDto);
    BiddingDto getBiddingById(String productId, String sellerId);
    void deleteBidding(String productId, String sellerId);
}
