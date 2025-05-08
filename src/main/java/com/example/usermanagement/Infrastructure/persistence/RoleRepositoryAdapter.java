package com.example.usermanagement.Infrastructure.persistence;
import com.example.usermanagement.Application.interfaces.RoleRepository;
import com.example.usermanagement.Domain.Role;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class RoleRepositoryAdapter implements RoleRepository {

    private final RoleJpaRepository roleJpaRepository;

    public RoleRepositoryAdapter(RoleJpaRepository roleJpaRepository) {
        this.roleJpaRepository = roleJpaRepository;
    }

    @Override
    public UUID save(Role role) {
        RoleJpaEntity entity = new RoleJpaEntity();
        entity.setId(role.getId());
        entity.setRoleName(role.getRoleName());
        roleJpaRepository.save(entity);
        return entity.getId();
    }

    @Override
    public Optional<Role> findById(UUID id) {
        return roleJpaRepository.findById(id)
                .map(entity -> new Role(entity.getId(), entity.getRoleName()));
    }
}