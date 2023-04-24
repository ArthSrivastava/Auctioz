package com.snoozingturtles.auctioz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    @Size(min = 3, max = 40, message = "Name should be between 3 to 40 characters long!")
    private String name;

    @Size(min = 15, max = 2000, message = "Description should be between 15 to 200 characters long!")
    private String description;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String imageName;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean isSoldOut;
    //Updated when bidding details are entered
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String biddingId;

    //Selected from front-end
    private String categoryId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String sellerId;
}
