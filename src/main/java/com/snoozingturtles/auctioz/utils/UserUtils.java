package com.snoozingturtles.auctioz.utils;

import com.snoozingturtles.auctioz.dto.UserDto;
import com.snoozingturtles.auctioz.exceptions.EntityNotFoundException;
import com.snoozingturtles.auctioz.models.User;
import com.snoozingturtles.auctioz.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserUtils {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    public UserDto getUserById(String userId) {
        User user = userRepo.findById(userId).orElseThrow(() ->
                new EntityNotFoundException("No user found with given id!"));
        return modelMapper.map(user, UserDto.class);
    }

    public List<UserDto> getAllUsers() {
        return userRepo.findAll().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
    }
}
