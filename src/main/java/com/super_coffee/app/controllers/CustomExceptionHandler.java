package com.super_coffee.app.controllers;

import com.super_coffee.app.exception.FieldAlreadyUsedException;
import com.super_coffee.app.exception.DocumentNotFountException;
import com.super_coffee.app.models.Error;
import com.super_coffee.app.models.ExceptionModel;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@AllArgsConstructor
public class CustomExceptionHandler extends ResponseEntityExceptionHandler
{
    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid( MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request )
    {
        BindingResult result = exception.getBindingResult();
        List<Error> errors = new ArrayList<>();
        result.getFieldErrors().forEach( error -> {
            String message = messageSource.getMessage( error, Locale.forLanguageTag("US") );
            errors.add( new Error( message, error.getField(), "Body" ) );
        });

        ExceptionModel responseBody = new ExceptionModel( new Date().toString(), status.value(), status, errors );

        return new ResponseEntity<>( responseBody, status );
    }

    @ExceptionHandler( value = { FieldAlreadyUsedException.class } )
    protected ResponseEntity<ExceptionModel> anyFieldAlreadyUsedException( FieldAlreadyUsedException exception )
    {
        Error error = new Error( exception.getMessage(), exception.getFieldName(), "Body" );
        ExceptionModel responseBody = new ExceptionModel( new Date().toString(), CONFLICT.value(), CONFLICT, List.of( error ) );
        return new ResponseEntity<>( responseBody, CONFLICT );
    }

    @ExceptionHandler( value = { DocumentNotFountException.class } )
    protected ResponseEntity<ExceptionModel> notFoundDocumentException( DocumentNotFountException exception )
    {
        Error error = new Error( exception.getMessage(), exception.getField(), "PathVariable" );
        ExceptionModel responseBody = new ExceptionModel( new Date().toString(), NOT_FOUND.value(), NOT_FOUND, List.of( error ) );
        return new ResponseEntity<>( responseBody, NOT_FOUND );
    }
}