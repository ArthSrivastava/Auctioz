package com.snoozingturtles.auctioz.controllers;

import com.snoozingturtles.auctioz.dto.RoleDto;
import com.snoozingturtles.auctioz.payloads.ApiResponse;
import com.snoozingturtles.auctioz.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<ApiResponse> createRole(@RequestBody RoleDto roleDto) {
        RoleDto role = roleService.createRole(roleDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{roleId}")
                .buildAndExpand(role.getId()).toUri();
        return ResponseEntity.created(uri)
                .body(ApiResponse.builder()
                        .message("Role created successfully!")
                        .success(true)
                        .build());
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable  String roleId) {
        RoleDto roleById = roleService.getRoleById(roleId);
        return ResponseEntity.ok(roleById);
    }

    @GetMapping
    public ResponseEntity<List<RoleDto>> getRoles() {
        List<RoleDto> roles = roleService.getRoles();
        return ResponseEntity.ok(roles);
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<ApiResponse> updateRole(@PathVariable String roleId,
                                                  @RequestBody RoleDto roleDto) {
        roleService.updateRole(roleId, roleDto);
        return ResponseEntity.ok(ApiResponse.builder()
                .message("Role updated successfully!")
                .success(true)
                .build());
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<ApiResponse> deleteRole(@PathVariable String roleId) {
        roleService.deleteRole(roleId);
        return ResponseEntity.ok(ApiResponse.builder()
                .message("Role deleted successfully!")
                .success(true)
                .build());
    }

}
