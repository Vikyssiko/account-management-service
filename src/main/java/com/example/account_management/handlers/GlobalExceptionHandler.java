package com.example.account_management.handlers;

import com.example.account_management.dto.ApiErrorDto;
import com.example.account_management.exceptions.UserAlreadySignedUpException;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    private ApiErrorDto handleBadRequestException(BadRequestException e) {
        log.warn(e.getMessage());
        return new ApiErrorDto(400, e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityNotFoundException.class)
    private ApiErrorDto handleEntityNotFoundException(EntityNotFoundException e) {
        log.error(e.getMessage());
        return new ApiErrorDto(400, e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserAlreadySignedUpException.class)
    private ApiErrorDto handleUserAlreadySignedUpException(UserAlreadySignedUpException e) {
        log.warn(e.getMessage());
        return new ApiErrorDto(400, e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ExpiredJwtException.class)
    private ApiErrorDto handleExpiredJwtException(ExpiredJwtException e) {
        log.warn(e.getMessage());
        return new ApiErrorDto(400, e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UsernameNotFoundException.class)
    private ApiErrorDto handleUsernameNotFoundException(UsernameNotFoundException e) {
        log.warn(e.getMessage());
        return new ApiErrorDto(401, e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    private ApiErrorDto handleBadCredentialsException(BadCredentialsException e) {
        log.warn(e.getMessage());
        return new ApiErrorDto(401, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        log.warn(e.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }
}
