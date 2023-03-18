package com.snoozingturtles.auctioz.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "bidding_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bidding {
    @Id
    private String id;
    private String productId;
    private long startBidPrice;
    private long currentBidPrice;
    private String currentBidderId;
    private LocalDateTime deadline;
}
