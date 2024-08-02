package com.example.account_management.handlers;

import com.example.account_management.dto.ApiErrorDto;
import com.example.account_management.exceptions.UserAlreadySignedUpException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
//@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    private ApiErrorDto handleBadRequestException(BadRequestException e) {
        log.error(e.getMessage());
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
        log.error(e.getMessage());
        return new ApiErrorDto(400, e.getMessage());
    }
}
