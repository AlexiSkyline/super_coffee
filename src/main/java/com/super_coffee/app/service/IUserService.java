package com.super_coffee.app.service;

import com.super_coffee.app.models.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    User save( User user );
    List<User> findAll();
    Optional<User> findByEmail( String email );
    int countUser();
}