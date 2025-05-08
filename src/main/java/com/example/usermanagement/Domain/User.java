package com.example.usermanagement.Domain;
import java.util.*;
public class User {
    private UUID id;
    private String name;
    private String email;
    private Set<Role> roles = new HashSet<>();

    public User(String name, String email) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
    }
    // Used when loading from DB (preserve ID)
    public User(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public Set<Role> getRoles() { return roles; }

    public void assignRole(Role role) {
        this.roles.add(role);
    }
}
