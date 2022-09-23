package com.super_coffee.app.service;

import com.super_coffee.app.models.domain.Role;

import java.util.Optional;

public interface IRoleService
{
    Role save( Role role );
    Optional<Role> findByDescription( String description );
}