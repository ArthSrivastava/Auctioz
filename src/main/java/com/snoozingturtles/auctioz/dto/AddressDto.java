package com.snoozingturtles.auctioz.dto;

import lombok.Data;

@Data
public class AddressDto {
    private String id;
    private String userId;
    private String line1;
    private String line2;
    private String city;
    private String state;
    private int pincode;
}
