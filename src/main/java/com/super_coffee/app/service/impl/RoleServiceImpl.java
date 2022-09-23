package com.super_coffee.app.service.impl;

import com.super_coffee.app.models.domain.Role;
import com.super_coffee.app.repository.IRoleRepository;
import com.super_coffee.app.service.IRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service @AllArgsConstructor
public class RoleServiceImpl implements IRoleService
{
    private final IRoleRepository roleRepository;

    @Override
    public Role save( Role role ) {
        return this.roleRepository.save( role );
    }

    @Override
    public Optional<Role> findByDescription( String description )
    {
        return this.roleRepository.findByDescription( description );
    }
}