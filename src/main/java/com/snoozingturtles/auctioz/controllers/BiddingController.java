package com.snoozingturtles.auctioz.controllers;

import com.snoozingturtles.auctioz.dto.BiddingDto;
import com.snoozingturtles.auctioz.payloads.ApiResponse;
import com.snoozingturtles.auctioz.services.BiddingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sellers")
@RequiredArgsConstructor
public class BiddingController {
    private final BiddingService biddingService;

    @PostMapping("/{sellerId}/products/{productId}/biddings")
    @PreAuthorize(value = "@userSecurity.hasUserId(authentication, #sellerId)")
    public ResponseEntity<ApiResponse> createBidding(@PathVariable String sellerId,
                                                     @PathVariable String productId,
                                                     @Valid @RequestBody BiddingDto biddingDto) {
        biddingService.createBidding(productId, sellerId, biddingDto);
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .message("Bidding details updated successfully!")
                        .success(true)
                        .build(),
                HttpStatus.CREATED);
    }

    @GetMapping("/{sellerId}/products/{productId}/biddings")
    public ResponseEntity<BiddingDto> getBiddingByProductId(@PathVariable String sellerId,
                                                     @PathVariable String productId) {
        return ResponseEntity.ok(biddingService.getBiddingById(productId, sellerId));
    }

    @PutMapping("/{sellerId}/products/{productId}/biddings")
    @PreAuthorize(value = "@userSecurity.hasUserId(authentication, #sellerId)")
    public ResponseEntity<ApiResponse> updateBidding(@PathVariable String sellerId,
                                                     @PathVariable String productId,
                                                     @RequestBody BiddingDto biddingDto) {
        biddingService.updateBidding(productId, sellerId, biddingDto);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("Bidding details updated successfully!")
                        .success(true)
                        .build()
        );
    }

    @DeleteMapping("/{sellerId}/products/{productId}/biddings")
    @PreAuthorize(value = "@userSecurity.hasUserId(authentication, #sellerId)")
    public ResponseEntity<ApiResponse> deleteBidding(@PathVariable String sellerId,
                                                     @PathVariable String productId) {
        biddingService.deleteBidding(productId, sellerId);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("Bidding deleted successfully")
                        .success(true)
                        .build()
        );
    }
}
