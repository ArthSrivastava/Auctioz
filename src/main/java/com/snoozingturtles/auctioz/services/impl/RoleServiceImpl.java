package com.snoozingturtles.auctioz.services.impl;

import com.snoozingturtles.auctioz.dto.RoleDto;
import com.snoozingturtles.auctioz.exceptions.EntityNotFoundException;
import com.snoozingturtles.auctioz.models.Role;
import com.snoozingturtles.auctioz.repositories.RoleRepo;
import com.snoozingturtles.auctioz.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepo roleRepo;
    private final ModelMapper modelMapper;
    @Override
    public RoleDto createRole(RoleDto roleDto) {
        Role role = roleRepo.save(modelMapper.map(roleDto, Role.class));
        return modelMapper.map(role, RoleDto.class);
    }

    @Override
    public void updateRole(String roleId, RoleDto roleDto) {
        RoleDto roleById = getRoleById(roleId);
        roleById.setName(roleDto.getName());
        roleRepo.save(modelMapper.map(roleById, Role.class));
    }

    @Override
    public List<RoleDto> getRoles() {
        return roleRepo.findAll()
                .stream()
                .map(role -> modelMapper.map(role, RoleDto.class))
                .toList();
    }

    @Override
    public RoleDto getRoleById(String roleId) {
        return modelMapper.map(roleRepo.findById(roleId), RoleDto.class);
    }

    @Override
    public void deleteRole(String roleId) {
        Role role = roleRepo.findById(roleId).orElseThrow(() -> new EntityNotFoundException("No such role found!"));
        roleRepo.delete(role);
    }
}
