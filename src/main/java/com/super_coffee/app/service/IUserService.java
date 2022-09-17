package com.super_coffee.app.service;

import com.super_coffee.app.models.User;

import java.util.List;

public interface IUserService {
    User save( User user );
    List<User> findAll();
    int countUser();
}