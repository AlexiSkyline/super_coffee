package com.super_coffee.app.service;

import com.super_coffee.app.models.domain.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService
{
    Category save( Category user );
    List<Category> findAll();
    Category findById( String id );
    Optional<Category> findByName( String name );
    Category delete( String id );
    Category update( String id, Category category );
    int countCategory();
}