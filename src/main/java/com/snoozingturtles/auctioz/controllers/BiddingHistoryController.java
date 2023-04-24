package com.snoozingturtles.auctioz.controllers;

import com.snoozingturtles.auctioz.dto.BiddingHistoryDto;
import com.snoozingturtles.auctioz.payloads.ApiResponse;
import com.snoozingturtles.auctioz.services.BiddingHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BiddingHistoryController {
    private final BiddingHistoryService biddingHistoryService;

    @PostMapping("/users/{userId}/products/{productId}/bids")
    @PreAuthorize(value = "@userSecurity.hasUserId(authentication, #userId)")
    public ResponseEntity<ApiResponse> createBid(@PathVariable String userId,
                                                 @PathVariable String productId,
                                                 @RequestBody BiddingHistoryDto biddingHistoryDto) {
        biddingHistoryService.createBid(productId, userId, biddingHistoryDto);
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .message("Bid successful!")
                        .success(true)
                        .build(),
                HttpStatus.CREATED);
    }
    @GetMapping("/products/{productId}/bids")
    public ResponseEntity<List<BiddingHistoryDto>> getAllBidsOfProduct(@PathVariable String productId) {
        return ResponseEntity.ok(biddingHistoryService.getAllBidsOfProduct(productId));
    }

    @GetMapping("/users/{userId}/products/{productId}/bids")
    public ResponseEntity<List<BiddingHistoryDto>> getAllBidsOfProductByUserId(@PathVariable String userId,
                                                                               @PathVariable String productId) {
        return ResponseEntity.ok(biddingHistoryService.getAllBidsOfProductByUserId(productId, userId));
    }

    @GetMapping("/users/{userId}/products/{productId}/bids/{bidId}")
    public ResponseEntity<BiddingHistoryDto> getBidOfProductByUserIdAndBidId(@PathVariable String userId,
                                                                                   @PathVariable String productId,
                                                                                   @PathVariable String bidId) {
        return ResponseEntity.ok(biddingHistoryService.getBidOfProductByUserIdAndBidId(productId, userId, bidId));
    }

    @GetMapping("/users/{userId}/bids")
    public ResponseEntity<List<BiddingHistoryDto>> getAllBidsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(biddingHistoryService.getAllBiddingsOfUser(userId));
    }
    @PutMapping("/users/{userId}/products/{productId}/bids/{bidId}")
    @PreAuthorize(value = "@userSecurity.hasUserId(authentication, #userId)")
    public ResponseEntity<ApiResponse> updateBidOfUser(@PathVariable String userId,
                                                       @PathVariable String productId,
                                                       @PathVariable String bidId,
                                                       @RequestBody BiddingHistoryDto biddingHistoryDto) {
        biddingHistoryService.updateBid(productId, userId, bidId, biddingHistoryDto);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("Bidding details for user updated successfully!")
                        .success(true)
                        .build()
        );
    }

    @DeleteMapping("/users/{userId}/products/{productId}/bids/{bidId}")
    @PreAuthorize(value = "@userSecurity.hasUserId(authentication, #userId)")
    public ResponseEntity<ApiResponse> deleteBidByProductIdAndUserIdAndBidId(@PathVariable String userId,
                                                                             @PathVariable String productId,
                                                                             @PathVariable String bidId) {
        biddingHistoryService.deleteBidByProductIdAndUserIdAndBidId(productId, userId, bidId);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("Bid deleted successfully")
                        .success(true)
                        .build()
        );
    }
}
