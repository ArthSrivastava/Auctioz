package com.snoozingturtles.auctioz.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class JwtAuthRequest {
    @Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @Size(min = 3, max = 30, message = "Password should be between 3 to 30 characters long!")
    private String password;
}
