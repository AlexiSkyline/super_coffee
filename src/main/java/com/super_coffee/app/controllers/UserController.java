package com.super_coffee.app.controllers;

import com.super_coffee.app.models.domain.User;
import com.super_coffee.app.response.ResponseHandler;
import com.super_coffee.app.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping( "/api/v1/users" )
@AllArgsConstructor
public class UserController
{
    private final IUserService userService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createUser( @RequestBody @Valid User user )
    {
        return ResponseHandler.responseBuild( CREATED, "User Created Successfully", 0,
                this.userService.save( user ) );
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllUsers()
    {
        return ResponseHandler.responseBuild( OK, "Requested All Users are given here", this.userService.countUser(),
                this.userService.findAll() );
    }

    @DeleteMapping( "{id}" )
    public ResponseEntity<Map<String, Object>> deleteUser( @PathVariable @Valid String id )
    {
        return ResponseHandler.responseBuild( OK, "User Delete Successfully", 0,
                this.userService.delete( id ) );
    }
}