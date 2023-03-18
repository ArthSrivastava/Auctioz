package com.snoozingturtles.auctioz.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String id;
    private String name;
    private String email;
    private String addressId;
    private List<String> roleId;
    @JsonIgnore
    private String accessToken;
    @JsonIgnore
    private String refreshToken;
}
