package com.super_coffee.app.security;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@ControllerAdvice
@AllArgsConstructor
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid( MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request ) {
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put( "timestamp", new Date() );
        responseBody.put( "status", status.value() );

        BindingResult result = ex.getBindingResult();
        List<String> errors = result.getFieldErrors()
                .stream()
                .map( error -> messageSource.getMessage( error, Locale.forLanguageTag("US")) )
                .toList();

        responseBody.put( "errors", errors );

        return new ResponseEntity<>( responseBody, headers, status );
    }
}