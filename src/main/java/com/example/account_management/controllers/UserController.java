package com.example.account_management.controllers;

import com.example.account_management.converters.AccountToAccountDtoConverter;
import com.example.account_management.dto.AccountDto;
import com.example.account_management.dto.AccountTransactionDto;
import com.example.account_management.entities.User;
import com.example.account_management.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/accounts")
public class UserController {
    private final UserService userService;
    private final AccountToAccountDtoConverter converter;

    @PutMapping("withdraw")
    public AccountDto withdraw(@Valid @RequestBody final AccountTransactionDto dto,
                               @AuthenticationPrincipal User user) throws BadRequestException {
        return converter.convert(userService.withdraw(dto, user));
    }

    @PutMapping("deposit")
    public AccountDto deposit(@Valid @RequestBody final AccountTransactionDto dto,
                              @AuthenticationPrincipal User user) throws BadRequestException {
        return converter.convert(userService.deposit(dto, user));
    }
}
