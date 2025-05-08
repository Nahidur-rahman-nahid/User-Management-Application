package com.example.usermanagement.Infrastructure.Controller;

import com.example.usermanagement.Application.UserService;
import com.example.usermanagement.Domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.usermanagement.Infrastructure.Controller.dto.CreateUserRequest;
import jakarta.validation.Valid;



import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UUID> createUser(@RequestBody @Valid CreateUserRequest request) {
        UUID id = userService.createUser(request.getName(), request.getEmail());
        return ResponseEntity.ok(id);
    }


    @PostMapping("/{userId}/assign-role/{roleId}")
    public ResponseEntity<String> assignRole(@PathVariable UUID userId, @PathVariable UUID roleId) {
        userService.assignRole(userId, roleId);
        return ResponseEntity.ok("Role assigned successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUser(id));
    }
}
