package com.asta.dev.exam2025.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApiExeption {
    private String message;
    private LocalDateTime dateTime;

    public ApiExeption(String message, LocalDateTime dateTime) {
        this.dateTime = dateTime;
        this.message = message;
    }
}
