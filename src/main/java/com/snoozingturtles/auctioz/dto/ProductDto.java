package com.snoozingturtles.auctioz.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    private String name;
    private String description;
    @JsonIgnore
    private String imageName;

    //Updated when bidding details are entered
    @JsonIgnore
    private String biddingId;

    //Selected from front-end
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String categoryId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String sellerId;
}
