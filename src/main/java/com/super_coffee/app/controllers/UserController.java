package com.super_coffee.app.controllers;

import com.super_coffee.app.models.User;
import com.super_coffee.app.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping( "/api/v1/users" )
@AllArgsConstructor
public class UserController {
    private final IUserService userService;

    @PostMapping
    public ResponseEntity<?> createUser( @RequestBody @Validated User user, BindingResult result ) {
        if( result.hasErrors() ) {
            Map<String, Object> errors = new HashMap<>();
            List<String> formatMessage = result.getFieldErrors().stream()
                    .map(error -> "The field '" + error.getField() + "' " + error.getDefaultMessage()).toList();
            errors.put( "errors", formatMessage );

            return ResponseEntity.status( BAD_REQUEST ).body( errors );
        }

        return ResponseEntity.status( CREATED ).body( this.userService.save( user ));
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getUsers() {
        Map<String, Object> response = new HashMap<>();
        response.put( "total", this.userService.countUser() );
        response.put( "users", this.userService.findAll() );

        return ResponseEntity.ok().body( response );
    }
}