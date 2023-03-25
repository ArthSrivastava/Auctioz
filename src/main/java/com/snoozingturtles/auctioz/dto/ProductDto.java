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
    @JsonIgnore
    private String biddingId;
    @JsonIgnore
    private String categoryId;
    private String sellerId;
}
