package com.example.usermanagement.Domain;
import java.util.*;
public class Role {
    private UUID id;
    private String roleName;

    public Role(String roleName) {
        this(UUID.randomUUID(), roleName); // default constructor
    }

    public Role(UUID id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public UUID getId() { return id; }
    public String getRoleName() { return roleName; }
}

