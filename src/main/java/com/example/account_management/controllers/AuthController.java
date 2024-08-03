package com.example.account_management.controllers;

import com.example.account_management.dto.AuthUserDto;
import com.example.account_management.dto.LogInResponse;
import com.example.account_management.dto.UserDto;
import com.example.account_management.entities.User;
import com.example.account_management.services.AuthenticationService;
import com.example.account_management.services.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    private final ConversionService conversionService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> register(@Valid @RequestBody AuthUserDto authUserDto) {
        User registeredUser = authenticationService.signup(authUserDto);
        return ResponseEntity.ok(conversionService.convert(registeredUser, UserDto.class));
    }

    @PostMapping("/login")
    public ResponseEntity<LogInResponse> authenticate(@Valid @RequestBody AuthUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LogInResponse loginResponse = new LogInResponse(jwtToken, jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}
