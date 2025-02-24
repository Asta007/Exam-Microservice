package com.asta.dev.exam2025.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiExeption handleEntityNotFoundException(NotFoundException e) {
        ApiExeption apiExeption = new ApiExeption(e.getMessage(), LocalDateTime.now());
        return apiExeption;
    }

    @ExceptionHandler(value = ExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiExeption handleEntityExistException(ExistException e) {
        ApiExeption apiExeption = new ApiExeption(e.getMessage(), LocalDateTime.now());
        return apiExeption;
    }
}
