package com.example.usermanagement.Application;

import com.example.usermanagement.Application.interfaces.RoleRepository;
import com.example.usermanagement.Application.interfaces.UserRepository;
import com.example.usermanagement.Domain.Role;
import com.example.usermanagement.Domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        String name = "Alice";
        String email = "alice@example.com";
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

        UUID fakeId = UUID.randomUUID();
        when(userRepository.save(any())).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            return user.getId();
        });

        UUID id = userService.createUser(name, email);

        verify(userRepository).save(userCaptor.capture());
        assertEquals(name, userCaptor.getValue().getName());
        assertEquals(email, userCaptor.getValue().getEmail());
        assertEquals(id, userCaptor.getValue().getId());
    }

    @Test
    void testAssignRoleToUser() {
        UUID userId = UUID.randomUUID();
        UUID roleId = UUID.randomUUID();

        User user = new User(userId, "Bob", "bob@example.com");
        Role role = new Role(roleId, "ADMIN");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(roleRepository.findById(roleId)).thenReturn(Optional.of(role));

        userService.assignRole(userId, roleId);

        assertTrue(user.getRoles().contains(role));
        verify(userRepository).save(user);
    }

    @Test
    void testAssignRole_UserNotFound() {
        UUID userId = UUID.randomUUID();
        UUID roleId = UUID.randomUUID();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            userService.assignRole(userId, roleId);
        });

        assertEquals("User not found", ex.getMessage());
    }

    @Test
    void testAssignRole_RoleNotFound() {
        UUID userId = UUID.randomUUID();
        UUID roleId = UUID.randomUUID();
        User user = new User(userId, "John", "john@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(roleRepository.findById(roleId)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            userService.assignRole(userId, roleId);
        });

        assertEquals("Role not found", ex.getMessage());
    }
}