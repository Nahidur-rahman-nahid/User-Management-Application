package com.example.usermanagement.Application;

import com.example.usermanagement.Application.interfaces.RoleRepository;
import com.example.usermanagement.Domain.Role;

import java.util.UUID;

public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public UUID createRole(String roleName) {
        Role role = new Role(roleName);
        return roleRepository.save(role);
    }

    public Role getRole(UUID id) {
        return roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
    }
}