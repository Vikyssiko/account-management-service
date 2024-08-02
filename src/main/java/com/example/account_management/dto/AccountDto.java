package com.example.account_management.dto;

import lombok.Data;

@Data
public class AccountDto {
    private long id;
    private String number;
    private boolean active;
    private double balance;
}
