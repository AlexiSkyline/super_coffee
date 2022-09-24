package com.super_coffee.app.service;

import com.super_coffee.app.models.domain.Role;

import java.util.Optional;

public interface IRoleService extends ICrudRepository<Role>
{
    Optional<Role> findByDescription( String description );
}