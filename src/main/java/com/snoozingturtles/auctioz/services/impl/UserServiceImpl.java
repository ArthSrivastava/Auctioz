package com.snoozingturtles.auctioz.services.impl;

import com.snoozingturtles.auctioz.dto.UserDto;
import com.snoozingturtles.auctioz.exceptions.EntityNotFoundException;
import com.snoozingturtles.auctioz.models.Role;
import com.snoozingturtles.auctioz.models.RoleEnum;
import com.snoozingturtles.auctioz.models.User;
import com.snoozingturtles.auctioz.repositories.RoleRepo;
import com.snoozingturtles.auctioz.repositories.UserRepo;
import com.snoozingturtles.auctioz.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepo roleRepo;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = roleRepo.findByName(RoleEnum.NORMAL.toString())
                .orElseThrow(() -> new EntityNotFoundException("No such role found!"));
        List<Role> roleIdList = new ArrayList<>();
        roleIdList.add(role);
        user.setRoleId(roleIdList);
        User savedUser = userRepo.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(String userId, UserDto userDto) {
        User user = modelMapper.map(getUserById(userId), User.class);
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        User savedUser = userRepo.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto getUserById(String userId) {
        User user = userRepo.findById(userId).orElseThrow(() ->
                new EntityNotFoundException("No user found with given id!"));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User userByEmail = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found!"));
        return modelMapper.map(userByEmail, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepo.findAll().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
    }

    @Override
    public void deleteUser(String userId) {
        User user = modelMapper.map(getUserById(userId), User.class);
        userRepo.delete(user);
    }
}
