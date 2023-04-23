package com.snoozingturtles.auctioz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    @Size(min = 3, max = 40, message = "Name should be between 3 to 40 characters long!")
    private String name;

    @Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 3, max = 30, message = "Password should be between 3 to 30 characters long!")
    private String password;

    private String addressId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<RoleDto> roleId;
}
