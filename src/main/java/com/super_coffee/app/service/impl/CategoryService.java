package com.super_coffee.app.service.impl;

import com.super_coffee.app.models.domain.Category;
import com.super_coffee.app.repository.ICategoryRepository;
import com.super_coffee.app.service.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service @AllArgsConstructor
public class CategoryService implements ICategoryService
{
    private final ICategoryRepository categoryRepository;

    @Override
    public Category save( Category category )
    {
        return this.categoryRepository.save( category );
    }

    @Override
    public List<Category> findAll()
    {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category findById( String id )
    {
        return this.categoryRepository.findById( id ).get();
    }

    @Override
    public Optional<Category> findByName( String name )
    {
        return this.categoryRepository.findByName( name );
    }

    @Override
    public Category delete( String id )
    {
        Category existed = this.categoryRepository.findById( id ).get();
        existed.setStatus( false );
        this.categoryRepository.save( existed );

        return existed;
    }

    @Override
    public Category update( String id, Category category )
    {
        category.set_id( id );
        return this.categoryRepository.save( category );
    }

    @Override
    public int countCategory()
    {
        return this.categoryRepository.countAllByStatusIsTrue();
    }
}