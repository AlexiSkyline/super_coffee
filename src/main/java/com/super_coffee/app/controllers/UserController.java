package com.super_coffee.app.controllers;

import com.super_coffee.app.models.User;
import com.super_coffee.app.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping( "/api/v1/users" )
@AllArgsConstructor
public class UserController
{
    private final IUserService userService;

    @PostMapping
    public ResponseEntity<?> createUser( @RequestBody @Valid User user )
    {
        return ResponseEntity.status( CREATED ).body( this.userService.save( user ) );
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllUsers()
    {
        Map<String, Object> response = new HashMap<>();
        response.put( "total", this.userService.countUser() );
        response.put( "users", this.userService.findAll() );

        return ResponseEntity.ok().body( response );
    }

    @DeleteMapping( "{id}" )
    public ResponseEntity<?> deleteUser( @PathVariable String id )
    {
        Map<String, Object> response = new HashMap<>();
        response.put( "user", this.userService.delete( id ) );
        return ResponseEntity.status( CREATED ).body( response );
    }
}