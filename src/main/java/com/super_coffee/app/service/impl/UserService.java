package com.super_coffee.app.service.impl;

import com.super_coffee.app.exception.FieldAlreadyUsedException;
import com.super_coffee.app.exception.DocumentNotFountException;
import com.super_coffee.app.models.domain.Role;
import com.super_coffee.app.models.domain.User;
import com.super_coffee.app.repository.IUserRepository;
import com.super_coffee.app.service.IRoleService;
import com.super_coffee.app.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Optional<User> existed = this.userRepository.findByEmail( user.getEmail() );
        if( existed.isPresent() ) {
            throw new FieldAlreadyUsedException( "E-mail", "User" );
        }
        Optional<Role> role = this.roleService.findByDescription( "USER_ROLE" );
        role.ifPresent( value -> user.setRoles( List.of(value) ) );
        user.setPassword( this.passwordEncoder.encode( user.getPassword() ) );

        return this.userRepository.save( user );
    }

    @Override
    @Transactional( readOnly = true )
    public List<User> findAll()
    {
        return this.userRepository.findAll();
    }

    @Override
    @Transactional( readOnly = true )
    public Optional<User> findByEmail( String email )
    {
        return this.userRepository.findByEmail( email );
    }

    @Override
    @Transactional
    public User delete( String id )
    {
        Optional<User> existed = this.userRepository.findById( id );
        if( existed.isEmpty() ) {
            throw new DocumentNotFountException( id, "User","ID" );
        }
        existed.get().setStatus( false );

        return this.userRepository.save( existed.get() );
    }

    @Override
    @Transactional( readOnly = true )
    public int countUser()
    {
        return this.userRepository.countAllByStatusIsTrue();
    }
}