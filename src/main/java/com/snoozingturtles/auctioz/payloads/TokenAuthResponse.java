package com.snoozingturtles.auctioz.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TokenAuthResponse {
    private String userId;
    private String accessToken;
}
