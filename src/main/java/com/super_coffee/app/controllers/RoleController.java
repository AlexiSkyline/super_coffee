package com.super_coffee.app.controllers;

import com.super_coffee.app.models.domain.Role;
import com.super_coffee.app.models.response.ResponseAction;
import com.super_coffee.app.models.response.ResponseGet;
import com.super_coffee.app.response.ResponseHandler;
import com.super_coffee.app.service.IRoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping( "/api/v1/role" )
@AllArgsConstructor
public class RoleController
{
    private final IRoleService roleService;

    @PostMapping
    public ResponseEntity<ResponseAction> createRole( @RequestBody @Valid Role role )
    {
        return ResponseHandler.responseBuild( CREATED, "Role Created Successfully", this.roleService.save( role ) );
    }

    @GetMapping
    public ResponseEntity<ResponseGet> getAllRoles()
    {
        return ResponseHandler.responseBuild( OK, "Requested All Roles are given here", this.roleService.countAllDocuments(),
                this.roleService.findAll() );
    }

    @GetMapping( "{id}" )
    public ResponseEntity<ResponseAction> getRoleById( @PathVariable String id )
    {
        return ResponseHandler.responseBuild( OK, "Requested Role By ID are given here", this.roleService.findById( id ) );
    }

    @PutMapping( "{id}" )
    public ResponseEntity<ResponseAction> updateRole( @PathVariable String id, @RequestBody @Valid Role role )
    {
        return ResponseHandler.responseBuild( OK, "Role Update Successfully", this.roleService.update( id, role ) );
    }

    @DeleteMapping( "{id}" )
    public ResponseEntity<ResponseAction> deleteRole( @PathVariable String id )
    {
        return ResponseHandler.responseBuild( OK, "Role Delete Successfully", this.roleService.delete( id ) );
    }
}