package com.super_coffee.app.service.impl;

import com.super_coffee.app.exception.DocumentNotFountException;
import com.super_coffee.app.exception.FieldAlreadyUsedException;
import com.super_coffee.app.models.domain.Role;
import com.super_coffee.app.repository.IRoleRepository;
import com.super_coffee.app.service.IRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service @AllArgsConstructor
public class RoleService implements IRoleService
{
    private final IRoleRepository roleRepository;

    @Override
    @Transactional
    public Role save( Role role )
    {
        Optional<Role> roleFound = this.roleRepository.findByDescription( role.getDescription() );
        if( roleFound.isPresent() ) {
            throw new FieldAlreadyUsedException( "Description", "Role" );
        }

        return this.roleRepository.save( role );
    }

    @Override
    @Transactional( readOnly = true )
    public List<Role> findAll()
    {
        return this.roleRepository.findAllByStatusTrue();
    }

    @Override
    @Transactional( readOnly = true )
    public Role findById( String id )
    {
        return this.getRoleById( id );
    }

    @Override
    @Transactional( readOnly = true )
    public Optional<Role> findByDescription( String description )
    {
        Optional<Role> roleFound = this.roleRepository.findByDescription( description );
        if( roleFound.isEmpty() ) {
            throw new DocumentNotFountException( description, "Role","Description" );
        }

        return roleFound;
    }

    @Override
    @Transactional
    public Role update( String id, Role role )
    {
        Role roleFound = this.getRoleById( id );
        Optional<Role> roleFoundByDescription = this.roleRepository.findByDescription( role.getDescription() );
        if( roleFoundByDescription.isPresent() && !roleFound.getDescription().equals( roleFoundByDescription.get().getDescription() ) ) {
            throw new FieldAlreadyUsedException( "Description", "Role" );
        }
        role.set_id( id );
        role.setUpdatedAt( LocalDateTime.now() );

        return this.roleRepository.save( role );
    }

    @Override
    @Transactional
    public Role delete( String id )
    {
        Role roleFound = this.getRoleById( id );
        roleFound.setUpdatedAt( LocalDateTime.now() );
        roleFound.setStatus( false );

        return this.roleRepository.save( roleFound );
    }

    @Override
    @Transactional( readOnly = true )
    public int countAllDocuments()
    {
        return this.roleRepository.countAllByStatusIsTrue();
    }

    private Role getRoleById( String id )
    {
        Optional<Role> roleFound = this.roleRepository.findById( id );
        if( roleFound.isEmpty() || !roleFound.get().getStatus() ) {
            throw new DocumentNotFountException( id, "Role","ID" );
        }

        return roleFound.get();
    }
}