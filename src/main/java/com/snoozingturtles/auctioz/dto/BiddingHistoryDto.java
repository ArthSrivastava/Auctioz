package com.snoozingturtles.auctioz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BiddingHistoryDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    private String userId;
    private String biddingAmount;
    private String productId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String timestamp;
}
