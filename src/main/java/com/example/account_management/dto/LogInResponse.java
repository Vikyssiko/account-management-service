package com.example.account_management.dto;

public record LogInResponse(
//        String email,
        String token,
        long expiresIn) {
}
