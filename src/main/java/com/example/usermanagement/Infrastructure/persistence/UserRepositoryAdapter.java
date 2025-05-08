package com.example.usermanagement.Infrastructure.persistence;

import com.example.usermanagement.Application.interfaces.UserRepository;
import com.example.usermanagement.Domain.Role;
import com.example.usermanagement.Domain.User;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public UserRepositoryAdapter(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public UUID save(User user) {
        UserJpaEntity entity = new UserJpaEntity();
        entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());

        Set<RoleJpaEntity> roleEntities = user.getRoles().stream().map(role -> {
            RoleJpaEntity r = new RoleJpaEntity();
            r.setId(role.getId());
            r.setRoleName(role.getRoleName());
            return r;
        }).collect(Collectors.toSet());

        entity.setRoles(roleEntities);
        userJpaRepository.save(entity);
        return entity.getId();
    }


    @Override
    public Optional<User> findById(UUID id) {
        return userJpaRepository.findById(id).map(entity -> {
            User user = new User(entity.getId(), entity.getName(), entity.getEmail());

            for (RoleJpaEntity roleEntity : entity.getRoles()) {
                Role role = new Role(roleEntity.getId(), roleEntity.getRoleName());
                user.assignRole(role);
            }

            return user;
        });
    }
}