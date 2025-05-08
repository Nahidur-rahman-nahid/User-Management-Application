package com.example.usermanagement.Application.interfaces;

import com.example.usermanagement.Domain.User;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    UUID save(User user);
    Optional<User> findById(UUID id);
}
