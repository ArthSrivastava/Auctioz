package com.snoozingturtles.auctioz.services.impl;

import com.snoozingturtles.auctioz.dto.AddressDto;
import com.snoozingturtles.auctioz.exceptions.EntityNotFoundException;
import com.snoozingturtles.auctioz.models.Address;
import com.snoozingturtles.auctioz.repositories.AddressRepo;
import com.snoozingturtles.auctioz.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final ModelMapper modelMapper;
    private final AddressRepo addressRepo;
    @Override
    public AddressDto createAddress(String userId, AddressDto addressDto) {
        addressDto.setUserId(userId);
        Address address = modelMapper.map(addressDto, Address.class);
        return modelMapper.map(addressRepo.save(address), AddressDto.class);
    }

    @Override
    public void updateAddress(String addressId, String userId, AddressDto addressDto) {
        AddressDto addressById = getAddressById(addressId, userId);
        addressById.setCity(addressDto.getCity());
        addressById.setPincode(addressDto.getPincode());
        addressById.setState(addressDto.getState());
        addressById.setLine1(addressDto.getLine1());
        addressById.setLine2(addressDto.getLine2());
        addressRepo.save(modelMapper.map(addressById, Address.class));
    }

    @Override
    public AddressDto getAddressById(String addressId, String userId) {
        Address address = addressRepo.findByIdAndUserId(addressId, userId)
                .orElseThrow(() -> new EntityNotFoundException("No address found!"));
        return modelMapper.map(address, AddressDto.class);
    }

    @Override
    public List<AddressDto> getAllAddressByUserId(String userId) {
        return addressRepo.findAllByUserId(userId)
                .stream()
                .map(address -> modelMapper.map(address, AddressDto.class))
                .toList();
    }

    @Override
    public void deleteAddress(String addressId, String userId) {
        addressRepo.delete(modelMapper.map(getAddressById(addressId, userId), Address.class));
    }
}
