package com.snoozingturtles.auctioz.configurations.security;

import com.snoozingturtles.auctioz.dto.UserDto;
import com.snoozingturtles.auctioz.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("userSecurity")
@RequiredArgsConstructor
public class UserSecurity {
    private final UserService userService;

    public boolean hasUserId(Authentication authentication, String userId) {
        UserDto userFromUserId = userService.getUserById(userId);
        String emailOfUserIdUser = userFromUserId.getEmail();
        String emailOfAuthenticatedUser = authentication.getName();
        return emailOfAuthenticatedUser.equals(emailOfUserIdUser);
    }
}
