package com.super_coffee.app.service.impl;

import com.super_coffee.app.exception.FieldAlreadyUsedException;
import com.super_coffee.app.exception.DocumentNotFountException;
import com.super_coffee.app.models.domain.User;
import com.super_coffee.app.models.domain.Role;
import com.super_coffee.app.repository.IUserRepository;
import com.super_coffee.app.service.IRoleService;
import com.super_coffee.app.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service @AllArgsConstructor
public class UserService implements IUserService
{
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IRoleService roleService;

    @Override
    @Transactional
    public User save( User user )
    {
        Optional<User> userFound = this.userRepository.findByEmail( user.getEmail() );
        if( userFound.isPresent() ) {
            throw new FieldAlreadyUsedException( "E-mail", "User" );
        }
        Role role = this.roleService.findByDescription( "USER_ROLE" );
        user.setRoles( List.of( role ) );
        user.setPassword( this.passwordEncoder.encode( user.getPassword() ) );

        return this.userRepository.save( user );
    }

    @Override
    @Transactional( readOnly = true )
    public List<User> findAll()
    {
        return this.userRepository.findAllByStatusTrue();
    }

    @Override
    @Transactional( readOnly = true )
    public User findById( String id )
    {
        Optional<User> userFound = this.userRepository.findById( id );
        if( userFound.isEmpty() ) {
            throw new DocumentNotFountException( id, "User", "ID" );
        }

        return userFound.get();
    }

    @Override
    @Transactional
    public User update( String id, User user )
    {
        Optional<User> userFound = this.userRepository.findById( id );
        if( userFound.isEmpty() ) {
            throw new DocumentNotFountException( id, "User","ID" );
        }

        Optional<User> emailFound = this.userRepository.findByEmail( user.getEmail() );
        if( emailFound.isPresent() && !userFound.get().getEmail().equals( emailFound.get().getEmail() ) ) {
            throw new FieldAlreadyUsedException( "E-mail", "User" );
        }

        user.set_id( id );
        user.setUpdatedAt( LocalDateTime.now() );
        user.setPassword( this.passwordEncoder.encode( user.getPassword() ) );
        user.setRoles( userFound.get().getRoles() );

        return this.userRepository.save( user );
    }

    @Override
    @Transactional
    public User addRoleUser( String id, Role role ) {
        Optional<User> userFound = this.userRepository.findById( id );
        if( userFound.isEmpty() ) {
            throw new DocumentNotFountException( id, "User","ID" );
        }
        Role roleFound =  this.roleService.findByDescription( role.getDescription() );
        userFound.get().addRole( roleFound );
        userFound.get().setUpdatedAt( LocalDateTime.now() );

        return this.userRepository.save( userFound.get() );
    }

    @Override
    @Transactional
    public User delete( String id )
    {
        Optional<User> userFound = this.userRepository.findById( id );
        if( userFound.isEmpty() || !userFound.get().getStatus() ) {
            throw new DocumentNotFountException( id, "User","ID" );
        }
        userFound.get().setUpdatedAt( LocalDateTime.now() );
        userFound.get().setStatus( false );

        return this.userRepository.save( userFound.get() );
    }

    @Override
    @Transactional( readOnly = true )
    public int countAllDocuments()
    {
        return this.userRepository.countAllByStatusIsTrue();
    }
}