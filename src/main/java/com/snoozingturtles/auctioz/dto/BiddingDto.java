package com.snoozingturtles.auctioz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

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

    @Pattern(regexp = "^(\\d{4})-(\\d{2})-(\\d{2})T(\\d{2}):(\\d{2}):(\\d{2})\\.(\\d{3})$", message = "Enter valid deadline in ISO8601 date format!")
    private String deadline;
}
