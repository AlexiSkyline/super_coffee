package com.super_coffee.app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor @Getter
public class ExceptionModel
{
    private final String timestamp;
    private final int httpError;
    private final HttpStatus httpStatus;
    List<String> errors;
}