package com.super_coffee.app.service;

import com.super_coffee.app.models.Role;
import com.super_coffee.app.models.User;
import com.super_coffee.app.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service @AllArgsConstructor
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IRoleService roleService;

    @Override
    public User save( User user ) {
        Optional<Role> role = this.roleService.findByDescription( "USER_ROLE" );
        role.ifPresent( value -> user.setRoles( List.of(value) ) );
        user.setPassword( this.passwordEncoder.encode( user.getPassword() ) );

        return this.userRepository.save( user );
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public int countUser() {
        return this.userRepository.countAllByStatusIsTrue();
    }
}