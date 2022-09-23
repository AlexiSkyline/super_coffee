package com.super_coffee.app.repository;

import com.super_coffee.app.models.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends MongoRepository<User, String>
{
    List<User> findAllByStatusTrue();
    Optional<User> findByEmail( String email );
    int countAllByStatusIsTrue();
}