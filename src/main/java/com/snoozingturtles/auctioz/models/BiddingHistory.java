package com.snoozingturtles.auctioz.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BiddingHistory {
    private String id;
    private String userId;
    private long biddingAmount;
    private String productId;
    private LocalDateTime timestamp;
}
