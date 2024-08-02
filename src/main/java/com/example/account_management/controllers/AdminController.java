package com.example.account_management.controllers;

import com.example.account_management.converters.AccountToAccountDtoConverter;
import com.example.account_management.dto.AccountDto;
import com.example.account_management.entities.Account;
import com.example.account_management.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin/accounts")
public class AdminController {
    private final AdminService adminService;
    private final ConversionService conversionService;

    @GetMapping
    public List<AccountDto> getAll() {
        return adminService.getAllAccounts().stream()
                .map(account -> conversionService.convert(account, AccountDto.class))
                .collect(Collectors.toList());
    }

    @PutMapping("{accountId}/block")
    public AccountDto lock(@PathVariable long accountId) {
        return conversionService.convert(adminService.blockAccount(accountId), AccountDto.class);
    }

    @PutMapping("{accountId}/unblock")
    public AccountDto unblock(@PathVariable long accountId) {
        return conversionService.convert(adminService.unblockAccount(accountId), AccountDto.class);
    }
}
