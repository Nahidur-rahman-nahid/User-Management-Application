package com.example.usermanagement.Configuration;

import com.example.usermanagement.Application.RoleService;
import com.example.usermanagement.Application.UserService;
import com.example.usermanagement.Application.interfaces.RoleRepository;
import com.example.usermanagement.Application.interfaces.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public UserService userService(UserRepository userRepository, RoleRepository roleRepository) {
        return new UserService(userRepository, roleRepository);
    }

    @Bean
    public RoleService roleService(RoleRepository roleRepository) {
        return new RoleService(roleRepository);
    }
}

