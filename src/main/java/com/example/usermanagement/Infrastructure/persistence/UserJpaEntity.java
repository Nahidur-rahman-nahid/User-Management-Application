package com.example.usermanagement.Infrastructure.persistence;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class UserJpaEntity {

    @Id
    private UUID id;

    private String name;

    private String email;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleJpaEntity> roles = new HashSet<>();

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Set<RoleJpaEntity> getRoles() { return roles; }
    public void setRoles(Set<RoleJpaEntity> roles) { this.roles = roles; }
}
