package com.super_coffee.app.repository;

import com.super_coffee.app.models.domain.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ICategoryRepository extends MongoRepository<Category, String>
{
    Optional<Category> findByName( String name );
    int countAllByStatusIsTrue();
}