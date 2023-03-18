package com.snoozingturtles.auctioz.services.impl;

import com.snoozingturtles.auctioz.dto.UserDto;
import com.snoozingturtles.auctioz.models.User;
import com.snoozingturtles.auctioz.repositories.UserRepo;
import com.snoozingturtles.auctioz.services.UserService;
import com.snoozingturtles.auctioz.utils.UserUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final UserUtils userUtils;

    public UserServiceImpl(UserRepo userRepo, ModelMapper modelMapper, UserUtils userUtils) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
        this.userUtils = userUtils;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User savedUser = userRepo.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(String userId, UserDto userDto) {
        User user = modelMapper.map(userUtils.getUserById(userId), User.class);
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        User savedUser = userRepo.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto getUserById(String userId) {
        return userUtils.getUserById(userId);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userUtils.getAllUsers();
    }

    @Override
    public void deleteUser(String userId) {
        User user = modelMapper.map(userUtils.getUserById(userId), User.class);
        userRepo.delete(user);
    }
}
