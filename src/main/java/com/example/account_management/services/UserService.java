package com.example.account_management.services;

import com.example.account_management.dto.AccountTransactionDto;
import com.example.account_management.entities.Account;
import com.example.account_management.entities.User;
import com.example.account_management.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Account withdraw(final AccountTransactionDto dto, final String userEmail) throws BadRequestException {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new EntityNotFoundException("User with email " + userEmail + " not found"));
        Account account = getUserAccount(user);
        double balance =  account.getBalance();
        double withdrawAmount = dto.getAmount();
        double newBalance = balance - withdrawAmount;
        if (newBalance < 0) {
            throw new BadRequestException("Insufficient funds");
        }
        user.getAccount().setBalance(newBalance);
        return userRepository.save(user).getAccount();
    }

    @Transactional
    public Account deposit(final AccountTransactionDto dto, final String userEmail) throws BadRequestException {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new EntityNotFoundException("User with email " + userEmail + " not found"));
        Account account = getUserAccount(user);
        double balance =  account.getBalance();
        double depositAmount = dto.getAmount();
        user.getAccount().setBalance(balance + depositAmount);
        return userRepository.save(user).getAccount();
    }

    private Account getUserAccount(User user) throws BadRequestException {
        Account account = user.getAccount();
        if (!account.isActive()) {
            throw new BadRequestException("Account is blocked");
        }
        return account;
    }
}
