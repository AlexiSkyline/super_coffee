package com.super_coffee.app.service.impl;

import com.super_coffee.app.exception.DocumentNotFountException;
import com.super_coffee.app.models.domain.Role;
import com.super_coffee.app.repository.IRoleRepository;
import com.super_coffee.app.service.IRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service @AllArgsConstructor
public class RoleService implements IRoleService
{
    private final IRoleRepository roleRepository;

    @Override
    @Transactional
    public Role save( Role role )
    {
        return this.roleRepository.save( role );
    }

    @Override
    @Transactional( readOnly = true )
    public Role findById( String id )
    {
        Optional<Role> roleFound = this.roleRepository.findById( id );
        if( roleFound.isEmpty() ) {
            throw new DocumentNotFountException( id, "Role","ID" );
        }

        return roleFound.get();
    }

    @Override
    @Transactional( readOnly = true )
    public Role findByDescription( String description )
    {
        Optional<Role> roleFound = this.roleRepository.findByDescription( description );
        if( roleFound.isEmpty() ) {
            throw new DocumentNotFountException( description, "Role","Description" );
        }

        return roleFound.get();
    }
}