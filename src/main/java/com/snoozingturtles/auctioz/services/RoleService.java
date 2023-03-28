package com.snoozingturtles.auctioz.services;

import com.snoozingturtles.auctioz.dto.RoleDto;

import java.util.List;

public interface RoleService {
    RoleDto createRole(RoleDto roleDto);
    void updateRole(String roleId, RoleDto roleDto);
    List<RoleDto> getRoles();
    RoleDto getRoleById(String roleId);
    void deleteRole(String roleId);
}
