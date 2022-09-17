package com.super_coffee.app.service;

import com.super_coffee.app.models.Role;
import com.super_coffee.app.repository.IRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service @AllArgsConstructor
public class RoleServiceImpl implements IRoleService {
    private final IRoleRepository roleRepository;

    @Override
    public Role save( Role role ) {
        return this.roleRepository.save( role );
    }

    @Override
    public Optional<Role> findByDescription( String description ) {
        return this.roleRepository.findByDescription( description );
    }
}