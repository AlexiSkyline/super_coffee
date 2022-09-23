package com.super_coffee.app.repository;

import com.super_coffee.app.models.domain.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ICategoryRepository extends MongoRepository<Category, String>
{
    List<Category> findAllByStatusTrue();
    Optional<Category> findByName( String name );
    int countAllByStatusIsTrue();
}