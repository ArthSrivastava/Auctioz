package com.snoozingturtles.auctioz.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BiddingDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String productId;

    @Pattern(regexp = "^\\d+$", message = "Enter valid start bid price!")
    private String startBidPrice;
    private String currentBidPrice;
    private String currentBidderId;
    private LocalDateTime deadline;
}
