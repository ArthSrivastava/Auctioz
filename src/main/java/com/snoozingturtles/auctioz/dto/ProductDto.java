package com.snoozingturtles.auctioz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    private String name;
    private String description;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String imageName;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean isSoldOut;
    //Updated when bidding details are entered
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String biddingId;

    //Selected from front-end
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String categoryId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String sellerId;
}
