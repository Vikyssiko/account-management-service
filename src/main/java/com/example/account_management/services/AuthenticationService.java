package com.example.account_management.services;

import com.example.account_management.dto.AuthUserDto;
import com.example.account_management.entities.User;
import com.example.account_management.exceptions.UserAlreadySignedUpException;
import com.example.account_management.repositories.RoleRepository;
import com.example.account_management.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public User signup(AuthUserDto input) {
        if (userRepository.findByEmail(input.getEmail()).isPresent()) {
            throw new UserAlreadySignedUpException("User with such email already exists");
        }
        User user = new User(input.getEmail(), passwordEncoder.encode(input.getPassword()));
        user.setRole(roleRepository.findByName("ROLE_USER").orElseThrow());
        return userRepository.save(user);
    }

    public User authenticate(AuthUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}
