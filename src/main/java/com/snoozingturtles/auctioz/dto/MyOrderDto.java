package com.snoozingturtles.auctioz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyOrderDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    
    private String userId;
    private String productId;
    private int amount;

//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String razorpayOrderId;

//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String status;

//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String paymentId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime timestamp;
}
