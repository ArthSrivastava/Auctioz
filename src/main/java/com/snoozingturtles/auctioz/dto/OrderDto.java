package com.snoozingturtles.auctioz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    private String userId;
    private String productId;
    private LocalDateTime timestamp;
}
