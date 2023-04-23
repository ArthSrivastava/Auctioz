package com.snoozingturtles.auctioz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddressDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String userId;

    @Size(min = 8, max = 60, message = "Address line 1 must be of length 3 to 40 characters!")
    private String line1;
    private String line2;

    @Size(min = 2, max = 30, message = "City name must be between 2 to 30 characters long!")
    private String city;

    @Size(min = 3, max = 30, message = "State must be 3 to 30 characters long!")
    private String state;

    @Pattern(regexp = "^[1-9][0-9]{5}$", message = "Enter a valid pincode!")
    private String pincode;
}
