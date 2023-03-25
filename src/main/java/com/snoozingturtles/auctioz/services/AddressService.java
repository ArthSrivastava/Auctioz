package com.snoozingturtles.auctioz.services;

import com.snoozingturtles.auctioz.dto.AddressDto;

import java.util.List;

public interface AddressService {
    AddressDto createAddress(String userId, AddressDto addressDto);
    void updateAddress(String addressId, String userId, AddressDto addressDto);
    AddressDto getAddressById(String addressId, String userId);
    List<AddressDto> getAllAddressByUserId(String userId);
    void deleteAddress(String addressId, String userId);
}
