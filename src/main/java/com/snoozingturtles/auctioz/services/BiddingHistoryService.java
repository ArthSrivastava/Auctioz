package com.snoozingturtles.auctioz.services;

import com.snoozingturtles.auctioz.dto.BiddingHistoryDto;

import java.util.List;

public interface BiddingHistoryService {
    void createBid(String productId, String userId, BiddingHistoryDto biddingHistoryDto);
    void updateBid(String productId, String userId, String bidId, BiddingHistoryDto biddingHistoryDto);
    List<BiddingHistoryDto> getAllBidsOfProduct(String productId);
    List<BiddingHistoryDto> getAllBiddingsOfUser(String userId);
    List<BiddingHistoryDto> getAllBidsOfProductByUserId(String productId, String userId);
    BiddingHistoryDto getBidOfProductByUserIdAndBidId(String productId, String userId, String bidId);
    void deleteBidByProductIdAndUserIdAndBidId(String productId, String userId, String bidId);
}
