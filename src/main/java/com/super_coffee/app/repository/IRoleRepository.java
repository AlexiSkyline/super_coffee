package com.super_coffee.app.repository;

import com.super_coffee.app.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IRoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByDescription( String description );
}