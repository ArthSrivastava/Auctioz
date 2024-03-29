package com.snoozingturtles.auctioz.controllers;

import com.snoozingturtles.auctioz.dto.UserDto;
import com.snoozingturtles.auctioz.payloads.ApiResponse;
import com.snoozingturtles.auctioz.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto user = userService.createUser(userDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}")
                .buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @GetMapping("/")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable String userId,
                                                  @Valid @RequestBody UserDto userDto) {
        userService.updateUser(userId, userDto);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("User updated successfully!")
                        .success(true)
                        .build()
        );
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("User deleted successfully")
                        .success(true)
                        .build()
        );
    }

}
