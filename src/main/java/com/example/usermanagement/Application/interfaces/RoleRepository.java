package com.example.usermanagement.Application.interfaces;

import com.example.usermanagement.Domain.Role;
import java.util.Optional;
import java.util.UUID;

public interface RoleRepository {
    UUID save(Role role);
    Optional<Role> findById(UUID id);
}