package com.snoozingturtles.auctioz.services;

import com.snoozingturtles.auctioz.dto.UserDto;

import java.util.List;


public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(String userId, UserDto userDto);
    UserDto getUserById(String userId);
    List<UserDto> getAllUsers();
    void deleteUser(String userId);
}
