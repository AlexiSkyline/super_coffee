package com.super_coffee.app.controllers;

import com.super_coffee.app.models.domain.Role;
import com.super_coffee.app.models.domain.User;
import com.super_coffee.app.models.response.ResponseAction;
import com.super_coffee.app.models.response.ResponseGet;
import com.super_coffee.app.response.ResponseHandler;
import com.super_coffee.app.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping( "/api/v1/user" )
@AllArgsConstructor
public class UserController
{
    private final IUserService userService;

    @PostMapping
    public ResponseEntity<ResponseAction> createUser(@RequestBody @Valid User user )
    {
        return ResponseHandler.responseBuild( CREATED, "User Created Successfully", this.userService.save( user ) );
    }

    @GetMapping
    public ResponseEntity<ResponseGet> getAllUsers()
    {
        return ResponseHandler.responseBuild( OK, "Requested All Users are given here", this.userService.countAllDocuments(),
                this.userService.findAll() );
    }

    @GetMapping( "{id}" )
    public ResponseEntity<ResponseAction> getUserById( @PathVariable String id )
    {
        return ResponseHandler.responseBuild( OK, "Requested User By ID are given here", this.userService.findById( id ) );
    }

    @PutMapping( "{id}" )
    public ResponseEntity<ResponseAction> updateUser( @PathVariable String id, @RequestBody @Valid User user )
    {
        return ResponseHandler.responseBuild( OK, "User Update Successfully", this.userService.update( id, user ) );
    }

    @PutMapping( "/add-role/{id}" )
    public ResponseEntity<ResponseAction> addRoleUser( @PathVariable String id, @RequestBody @Valid Role role )
    {
        return ResponseHandler.responseBuild( OK, "Add Role User Successfully", this.userService.addRoleUser( id, role ) );
    }

    @DeleteMapping( "{id}" )
    public ResponseEntity<ResponseAction> deleteUser( @PathVariable @Valid String id )
    {
        return ResponseHandler.responseBuild( OK, "User Delete Successfully", this.userService.delete( id ) );
    }
}