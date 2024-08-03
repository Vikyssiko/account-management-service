package com.example.account_management.services;

import com.example.account_management.entities.Account;
import com.example.account_management.events.UserCreationEvent;
import com.example.account_management.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    @EventListener
    public void createAccount(UserCreationEvent event) {
        String number = generateAccountNumber();
        while (accountRepository.findByNumber(number).isPresent()) {
            number = generateAccountNumber();
        }
        Account account = new Account(event.getUser(), number);
        accountRepository.save(account);
}

    private String generateAccountNumber() {
        int leftLimit = 48;
        int rightLimit = 57;

        return new Random().ints(leftLimit, rightLimit + 1)
                .limit(10)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
