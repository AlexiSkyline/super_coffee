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
        Optional<Category> categoryFound = this.categoryRepository.findByName( category.getName() );
        if( categoryFound.isPresent() ) {
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
        Optional<Category> categoryFound = this.categoryRepository.findById( id );
        if( categoryFound.isEmpty() ) {
            throw new DocumentNotFountException( id, "Category", "ID" );
        }

        return categoryFound.get();
    }

    @Override
    @Transactional
    public Category update( String id, Category category )
    {
        Optional<Category> categoryFound = this.categoryRepository.findById( id );
        if( categoryFound.isEmpty() ) {
            throw new DocumentNotFountException( id, "Category","ID" );
        }

        Optional<Category> categoryFoundByName = this.categoryRepository.findByName( category.getName() );
        if( categoryFoundByName.isPresent() && !categoryFound.get().getName().equals( categoryFoundByName.get().getName() ) ) {
            throw new FieldAlreadyUsedException( "Name", "Category" );
        }

        category.set_id( id );
        category.setUpdatedAt( LocalDateTime.now() );
        return this.categoryRepository.save( category );
    }

    @Override
    @Transactional
    public Category delete( String id )
    {
        Optional<Category> categoryFound = this.categoryRepository.findById( id );
        if( categoryFound.isEmpty() || !categoryFound.get().getStatus() ) {
            throw new DocumentNotFountException( id, "Category", "ID" );
        }
        categoryFound.get().setUpdatedAt( LocalDateTime.now() );
        categoryFound.get().setStatus( false );

        return this.categoryRepository.save( categoryFound.get() );
    }

    @Override
    @Transactional( readOnly = true )
    public int countAllDocuments()
    {
        return this.categoryRepository.countAllByStatusIsTrue();
    }
}