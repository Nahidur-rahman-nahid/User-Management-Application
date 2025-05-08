package com.example.usermanagement.Application;

import com.example.usermanagement.Application.interfaces.RoleRepository;
import com.example.usermanagement.Domain.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
   @Test
    void testCreateRole() {
        String roleName = "ADMIN";
        Role role = new Role(UUID.randomUUID(), roleName);

        when(roleRepository.save(any())).thenReturn(role.getId());

        UUID resultId = roleService.createRole(roleName);

        assertNotNull(resultId);
        verify(roleRepository, times(1)).save(any());
    }

    @Test
    void testGetRoleFound() {
        UUID id = UUID.randomUUID();
        Role role = new Role(id, "USER");

        when(roleRepository.findById(id)).thenReturn(Optional.of(role));
        Role result = roleService.getRole(id);

        assertEquals(role, result);
    }

    @Test
    void testGetRoleNotFound() {
        UUID id = UUID.randomUUID();
        when(roleRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            roleService.getRole(id);
        });

        assertEquals("Role not found", ex.getMessage());
    }
}
