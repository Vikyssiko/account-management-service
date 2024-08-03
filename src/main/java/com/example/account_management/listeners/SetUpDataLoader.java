package com.example.account_management.listeners;

import com.example.account_management.configs.AdminConfig;
import com.example.account_management.dto.AuthUserDto;
import com.example.account_management.entities.Role;
import com.example.account_management.entities.User;
import com.example.account_management.repositories.RoleRepository;
import com.example.account_management.repositories.UserRepository;
import com.example.account_management.services.AuthenticationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class SetUpDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private boolean alreadySetup = false;
    private final AdminConfig adminConfig;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;

    @Override
    @Transactional
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
        if (alreadySetup)
            return;

        createRoleIfNotFound("ROLE_USER");
        Role adminRole = createRoleIfNotFound("ROLE_ADMIN");
        if (userRepository.findByRole(adminRole).isEmpty()) {
            User user = new User(
                    adminConfig.getEmail(),
                    passwordEncoder.encode(adminConfig.getPassword()),
                    adminRole
            );
            userRepository.save(user);
        }
//        addTestUsers();

        alreadySetup = true;
    }

    @Transactional
    Role createRoleIfNotFound(String name) {
        Role role = roleRepository.findByName(name).orElse(null);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
        return role;
    }

    @Transactional
    void addTestUsers() {
        authenticationService.signup(new AuthUserDto("test_user@test.com", "test_user"));
        authenticationService.signup(new AuthUserDto("test@test.com", "test"));
    }
}
