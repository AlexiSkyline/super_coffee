package com.super_coffee.app.controllers;

import com.super_coffee.app.models.User;
import com.super_coffee.app.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping( "/api/v1/users" )
@AllArgsConstructor
public class UserController {
    private final IUserService userService;

    @PostMapping
    public ResponseEntity<?> createUser( @RequestBody @Valid User user ) {
        Optional<User> existed = this.userService.findByEmail( user.getEmail() );
        if( existed.isPresent() ) {
            Map<String, Object> errors = this.buildMessageError( CONFLICT, List.of( "Email '" + user.getEmail() + "' already exists." ) );
            return ResponseEntity.status( CONFLICT ).body( errors );
        }

        return ResponseEntity.status( CREATED ).body( this.userService.save( user ) );
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getUsers() {
        Map<String, Object> response = new HashMap<>();
        response.put( "total", this.userService.countUser() );
        response.put( "users", this.userService.findAll() );

        return ResponseEntity.ok().body( response );
    }

    private Map<String, Object> buildMessageError( HttpStatus status, List<Object> errors ) {
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put( "timestamp", new Date() );
        responseBody.put( "status", status.value() );
        responseBody.put( "errors", errors );

        return responseBody;
    }
}