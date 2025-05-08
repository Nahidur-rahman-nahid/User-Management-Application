package com.example.usermanagement.Infrastructure.Controller;

import com.example.usermanagement.Application.RoleService;
import com.example.usermanagement.Domain.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.usermanagement.Infrastructure.Controller.dto.CreateRoleRequest;
import jakarta.validation.Valid;

import java.util.UUID;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<UUID> createRole(@RequestBody @Valid CreateRoleRequest request) {
        UUID id = roleService.createRole(request.getRoleName());
        return ResponseEntity.ok(id);
    }

}
