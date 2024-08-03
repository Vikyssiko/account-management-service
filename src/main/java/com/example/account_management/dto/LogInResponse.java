package com.example.account_management.dto;

public record LogInResponse(
        String token,
        long expiresIn) {
}
