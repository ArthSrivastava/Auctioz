package com.snoozingturtles.auctioz.services.impl;

import com.snoozingturtles.auctioz.dto.UserDto;
import com.snoozingturtles.auctioz.models.User;
import com.snoozingturtles.auctioz.repositories.UserRepo;
import com.snoozingturtles.auctioz.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepo userRepo, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User savedUser = userRepo.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }
}
