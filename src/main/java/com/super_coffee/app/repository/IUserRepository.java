package com.super_coffee.app.repository;

import com.super_coffee.app.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepository extends MongoRepository<User, String> {
    int countAllByStatusIsTrue();
}