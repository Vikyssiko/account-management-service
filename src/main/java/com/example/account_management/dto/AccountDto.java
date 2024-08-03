package com.example.account_management.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountDto {
    @NotNull
    private long id;
    @Pattern(regexp = "\\d{10}", message = "Must consist of 10 digits")
    private String number;
    private boolean active;
    private double balance;
}
