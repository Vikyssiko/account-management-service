package com.example.account_management.services;

import com.example.account_management.dto.AccountTransactionDto;
import com.example.account_management.entities.Account;
import com.example.account_management.entities.User;
import com.example.account_management.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Account withdraw(final AccountTransactionDto dto, final User user) throws BadRequestException {
        Account account = getUserAccountIfNotBlocked(user);
        double balance =  account.getBalance();
        double withdrawAmount = dto.getAmount();
        double newBalance = (balance * 100 - withdrawAmount * 100) / 100;
        if (newBalance < 0) {
            throw new BadRequestException("Insufficient funds");
        }
        user.getAccount().setBalance(newBalance);
        return userRepository.save(user).getAccount();
    }

    @Transactional
    public Account deposit(final AccountTransactionDto dto, final User user) throws BadRequestException {
        Account account = getUserAccountIfNotBlocked(user);
        double balance =  account.getBalance();
        double depositAmount = dto.getAmount();
        double newBalance = (balance * 100 + depositAmount * 100) / 100;
        user.getAccount().setBalance(newBalance);
        return userRepository.save(user).getAccount();
    }

    private Account getUserAccountIfNotBlocked(User user) throws BadRequestException {
        Account account = user.getAccount();
        if (!account.isActive()) {
            throw new BadRequestException("Account is blocked");
        }
        return account;
    }
}
