package com.example.usermanagement.Application;

import com.example.usermanagement.Application.interfaces.RoleRepository;
import com.example.usermanagement.Application.interfaces.UserRepository;
import com.example.usermanagement.Domain.Role;
import com.example.usermanagement.Domain.User;

import java.util.UUID;

public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UUID createUser(String name, String email) {
        User user = new User(name, email);
        return userRepository.save(user);
    }

    public User getUser(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void assignRole(UUID userId, UUID roleId) {
        User user = getUser(userId);
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        user.assignRole(role);
        userRepository.save(user); // re-save user with updated roles
    }
}