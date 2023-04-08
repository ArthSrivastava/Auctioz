package com.snoozingturtles.auctioz.controllers;

import com.snoozingturtles.auctioz.configurations.security.jwtutil.JwtTokenHelper;
import com.snoozingturtles.auctioz.dto.UserDto;
import com.snoozingturtles.auctioz.exceptions.LoginException;
import com.snoozingturtles.auctioz.payloads.JwtAuthRequest;
import com.snoozingturtles.auctioz.payloads.TokenAuthResponse;
import com.snoozingturtles.auctioz.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenHelper jwtTokenHelper;

    @PostMapping("/login")
    public ResponseEntity<TokenAuthResponse> generateToken(@RequestBody JwtAuthRequest jwtAuthRequest) {
        String email = jwtAuthRequest.getEmail();
        String password = jwtAuthRequest.getPassword();
        this.authenticate(email, password);
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        String jwtToken = this.jwtTokenHelper.generateToken(userDetails);

        //Fetching user to send user id in response
        UserDto userDto = userService.getUserByEmail(email);
        TokenAuthResponse tokenAuthResponse = new TokenAuthResponse(userDto.getId(), jwtToken);
        return new ResponseEntity<>(tokenAuthResponse, HttpStatus.OK);
    }

    private void authenticate(String email, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(email, password);
        try {
            this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (BadCredentialsException e) {
            throw new LoginException("Invalid email or password");
        }
    }
}
