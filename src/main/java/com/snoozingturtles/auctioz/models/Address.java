package com.snoozingturtles.auctioz.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    private String id;
    private String userId;
    private String line1;
    private String line2;
    private String city;
    private String state;
    private String pincode;
}
