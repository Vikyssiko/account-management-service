package com.example.account_management.exceptions;

import org.springframework.security.core.AuthenticationException;

public class UserAlreadySignedUpException extends AuthenticationException {
    public UserAlreadySignedUpException(String msg) {
        super(msg);
    }
}
