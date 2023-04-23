package com.snoozingturtles.auctioz.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bidding_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bidding {
    @Id
    private String id;
    private String productId;
    private String startBidPrice;
    private String currentBidPrice;
    private String currentBidderId;
    private String deadline;
}
