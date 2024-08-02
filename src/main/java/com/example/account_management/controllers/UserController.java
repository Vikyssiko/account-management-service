package com.example.account_management.controllers;

import com.example.account_management.converters.AccountToAccountDtoConverter;
import com.example.account_management.dto.AccountDto;
import com.example.account_management.dto.AccountTransactionDto;
import com.example.account_management.entities.Account;
import com.example.account_management.entities.User;
import com.example.account_management.services.JwtService;
import com.example.account_management.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/accounts")
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;
    private final AccountToAccountDtoConverter converter;
    @PutMapping("withdraw")
    public AccountDto withdraw(@RequestBody final AccountTransactionDto dto,
                               HttpServletRequest request) throws BadRequestException {
        final String userEmail = extractEmail(request);
        return converter.convert(userService.withdraw(dto, userEmail));
    }

    @PutMapping("deposit")
    public AccountDto deposit(@RequestBody final AccountTransactionDto dto,
                        HttpServletRequest request) throws BadRequestException {
        final String userEmail = extractEmail(request);
        return converter.convert(userService.deposit(dto, userEmail));
    }

    private String extractEmail(HttpServletRequest request) {
        final String jwt = request.getHeader("Authorization").substring(7);
        return jwtService.extractUsername(jwt);
    }
}
