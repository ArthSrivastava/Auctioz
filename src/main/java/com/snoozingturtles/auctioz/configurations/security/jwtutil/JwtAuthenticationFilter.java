package com.snoozingturtles.auctioz.configurations.security.jwtutil;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snoozingturtles.auctioz.payloads.ApiResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.FilterChain;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenHelper jwtTokenHelper;
    private final UserDetailsService userDetailsService;
    private final ObjectMapper objectMapper;


    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        String requestToken = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;
        String message;
        if(requestToken != null && requestToken.startsWith("Bearer") && requestToken.length() > 6) {
            jwtToken = requestToken.substring(7);
            try {
                username = this.jwtTokenHelper.extractUsername(jwtToken);
            } catch (IllegalArgumentException e) {
                message = "Unable to get JWT token";
                handleJwtExceptions(response, message);
                return;
            } catch (ExpiredJwtException e) {
                message = "JWT token has expired!";
                handleJwtExceptions(response, message);
                return;
            } catch (MalformedJwtException e) {
                message = "Invalid jwt";
                handleJwtExceptions(response, message);
                return;
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (this.jwtTokenHelper.isTokenValid(jwtToken, userDetails)) {
                //do authentication
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void handleJwtExceptions(HttpServletResponse response, String message) throws IOException {
        ApiResponse apiResponse = ApiResponse.builder()
                .message(message)
                .success(false)
                .build();
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
    }
}
