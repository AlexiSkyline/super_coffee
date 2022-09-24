package com.super_coffee.app.service;

import com.super_coffee.app.models.domain.Role;

public interface IRoleService
{
    Role save( Role role );
    Role findById( String id );
    Role findByDescription( String description );
}