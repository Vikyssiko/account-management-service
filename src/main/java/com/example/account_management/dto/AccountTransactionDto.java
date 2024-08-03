package com.example.account_management.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountTransactionDto {
    @NotNull
    @DecimalMin(value = "0.0")
    @Digits(integer = 10, fraction = 2)
    private double amount;
}
