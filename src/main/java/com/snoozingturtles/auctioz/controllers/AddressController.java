package com.snoozingturtles.auctioz.controllers;

import com.snoozingturtles.auctioz.dto.AddressDto;
import com.snoozingturtles.auctioz.payloads.ApiResponse;
import com.snoozingturtles.auctioz.services.AddressService;
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
public class AddressController {
    private final AddressService addressService;
    @PostMapping("/{userId}/addresses")
    @PreAuthorize(value = "@userSecurity.hasUserId(authentication, #userId)")
    public ResponseEntity<ApiResponse> createAddress(@PathVariable String userId,
                                                     @Valid @RequestBody AddressDto addressDto) {
        AddressDto address = addressService.createAddress(userId, addressDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{addressId}")
                .buildAndExpand(address.getId()).toUri();
        return ResponseEntity.created(uri).body(
                ApiResponse.builder()
                        .message("Address created successfully!")
                        .success(true)
                        .build()
        );
    }
    @GetMapping("/{userId}/addresses")
    @PreAuthorize(value = "@userSecurity.hasUserId(authentication, #userId)")
    public ResponseEntity<List<AddressDto>> getAllAddressByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(addressService.getAllAddressByUserId(userId));
    }

    @GetMapping("/{userId}/addresses/{addressId}")
    @PreAuthorize(value = "@userSecurity.hasUserId(authentication, #userId)")
    public ResponseEntity<AddressDto> getAddressById(@PathVariable String userId,
                                                      @PathVariable String addressId) {
        return ResponseEntity.ok(addressService.getAddressById(addressId, userId));
    }

    @PutMapping("/{userId}/addresses/{addressId}")
    @PreAuthorize(value = "@userSecurity.hasUserId(authentication, #userId)")
    public ResponseEntity<ApiResponse> updateAddress(@PathVariable String userId,
                                                      @PathVariable String addressId,
                                                      @RequestBody AddressDto addressDto) {
        addressService.updateAddress(addressId, userId, addressDto);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("Address updated successfully!")
                        .success(true)
                        .build()
        );
    }

    @DeleteMapping("/{userId}/addresses/{addressId}")
    @PreAuthorize(value = "@userSecurity.hasUserId(authentication, #userId)")
    public ResponseEntity<ApiResponse> deleteAddress(@PathVariable String userId,
                                                     @PathVariable String addressId) {
        addressService.deleteAddress(addressId, userId);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("Address deleted successfully")
                        .success(true)
                        .build()
        );
    }
}
