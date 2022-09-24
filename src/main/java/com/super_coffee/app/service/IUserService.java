package com.super_coffee.app.service;

import com.super_coffee.app.models.domain.Role;
import com.super_coffee.app.models.domain.User;

public interface IUserService extends ICrudRepository<User>
{
    User addRoleUser( String id, Role role );
}