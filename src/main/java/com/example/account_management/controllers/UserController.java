package com.example.account_management.controllers;

import com.example.account_management.converters.AccountToAccountDtoConverter;
import com.example.account_management.dto.AccountDto;
import com.example.account_management.dto.AccountTransactionDto;
import com.example.account_management.entities.User;
import com.example.account_management.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/accounts")
public class UserController {
    private final UserService userService;
    private final ConversionService converter;

    @GetMapping
    public AccountDto getAccount(@AuthenticationPrincipal User user) {
        return converter.convert(user.getAccount(), AccountDto.class);
    }

    @PutMapping("withdraw")
    public AccountDto withdraw(@Valid @RequestBody final AccountTransactionDto dto,
                               @AuthenticationPrincipal User user) throws BadRequestException {
        return converter.convert(userService.withdraw(dto, user), AccountDto.class);
    }

    @PutMapping("deposit")
    public AccountDto deposit(@Valid @RequestBody final AccountTransactionDto dto,
                              @AuthenticationPrincipal User user) throws BadRequestException {
        return converter.convert(userService.deposit(dto, user), AccountDto.class);
    }
}
