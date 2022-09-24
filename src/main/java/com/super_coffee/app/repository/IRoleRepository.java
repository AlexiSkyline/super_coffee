package com.super_coffee.app.repository;

import com.super_coffee.app.models.domain.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface IRoleRepository extends MongoRepository<Role, String>
{
    List<Role> findAllByStatusTrue();
    Optional<Role> findByDescription( String description );
    int countAllByStatusIsTrue();
}