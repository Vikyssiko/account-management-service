package com.example.account_management.configs;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class AdminConfig {
    @Value("${admin.email}")
    private String email;
    @Value("${admin.password}")
    private String password;
}
