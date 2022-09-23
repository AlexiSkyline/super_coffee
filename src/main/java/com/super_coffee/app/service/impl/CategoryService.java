package com.super_coffee.app.service.impl;

import com.super_coffee.app.exception.DocumentNotFountException;
import com.super_coffee.app.exception.FieldAlreadyUsedException;
import com.super_coffee.app.models.domain.Category;
import com.super_coffee.app.repository.ICategoryRepository;
import com.super_coffee.app.service.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service @AllArgsConstructor
public class CategoryService implements ICategoryService
{
    private final ICategoryRepository categoryRepository;

    @Override
    @Transactional
    public Category save( Category category )
    {
        Optional<Category> excited = this.categoryRepository.findByName( category.getName() );
        if( excited.isPresent() ) {
            throw new FieldAlreadyUsedException( "Name", "Category" );
        }

        return this.categoryRepository.save( category );
    }

    @Override
    @Transactional( readOnly = true )
    public List<Category> findAll()
    {
        return this.categoryRepository.findAllByStatusTrue();
    }

    @Override
    @Transactional( readOnly = true )
    public Category findById( String id )
    {
        Optional<Category> existed = this.categoryRepository.findById( id );
        if( existed.isEmpty() ) {
            throw new DocumentNotFountException( id, "Category","ID" );
        }

        return existed.get();
    }

    @Override
    @Transactional( readOnly = true )
    public Optional<Category> findByName( String name )
    {
        return this.categoryRepository.findByName( name );
    }

    @Override
    @Transactional
    public Category delete( String id )
    {
        Optional<Category> existed = this.categoryRepository.findById( id );
        if( existed.isEmpty() || !existed.get().isStatus() ) {
            throw new DocumentNotFountException( id, "Category","ID" );
        }

        existed.get().setStatus( false );

        return this.categoryRepository.save( existed.get() );
    }

    @Override
    @Transactional
    public Category update( String id, Category category )
    {
        Optional<Category> existed = this.categoryRepository.findById( id );
        if( existed.isEmpty() ) {
            throw new DocumentNotFountException( id, "Category","ID" );
        }

        Optional<Category> excitedName = this.categoryRepository.findByName( category.getName() );
        if( excitedName.isPresent() && !existed.get().getName().equals( excitedName.get().getName() ) ) {
            throw new FieldAlreadyUsedException( "Name", "Category" );
        }

        category.set_id( id );
        category.setUpdatedAt( LocalDateTime.now() );
        return this.categoryRepository.save( category );
    }

    @Override
    @Transactional( readOnly = true )
    public int countCategory()
    {
        return this.categoryRepository.countAllByStatusIsTrue();
    }
}