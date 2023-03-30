package com.snoozingturtles.auctioz.services;

import com.snoozingturtles.auctioz.dto.BiddingDto;

import java.util.List;

public interface BiddingService {
    BiddingDto createBidding(String productId, String sellerId, BiddingDto biddingDto);
    void updateBidding(String productId, String sellerId, BiddingDto biddingDto);
    BiddingDto getBiddingById(String productId, String sellerId);
    List<BiddingDto> getAllBiddings();
    void deleteBidding(String productId, String sellerId);
}
