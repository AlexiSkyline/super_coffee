package com.super_coffee.app.service.impl;

import com.super_coffee.app.exception.DocumentNotFountException;
import com.super_coffee.app.exception.FieldAlreadyUsedException;
import com.super_coffee.app.models.domain.Product;
import com.super_coffee.app.repository.IProductRepository;
import com.super_coffee.app.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service @AllArgsConstructor
public class ProductService implements IProductService
{
    private final IProductRepository productRepository;

    @Override
    @Transactional
    public Product save( Product product )
    {
        Optional<Product> excited = this.productRepository.findByName( product.getName() );
        if( excited.isPresent() ) {
            throw new FieldAlreadyUsedException( "Name", "Product" );
        }

        return this.productRepository.save( product );
    }

    @Override
    @Transactional( readOnly = true )
    public List<Product> findAll()
    {
        return this.productRepository.findAll();
    }

    @Override
    @Transactional( readOnly = true )
    public Product findById( String id )
    {
        Optional<Product> existed = this.productRepository.findById( id );
        if( existed.isEmpty() ) {
            throw new DocumentNotFountException( id, "Product", "ID" );
        }

        return existed.get();
    }

    @Override
    @Transactional
    public Product update( String id, Product product )
    {
        Optional<Product> existed = this.productRepository.findById( id );
        if( existed.isEmpty() ) {
            throw new DocumentNotFountException( id, "Product","ID" );
        }

        Optional<Product> excitedName = this.productRepository.findByName( product.getName() );
        if( excitedName.isPresent() && !existed.get().getName().equals( excitedName.get().getName() ) ) {
            throw new FieldAlreadyUsedException( "Name", "Product" );
        }

        product.set_id( id );
        product.setUpdatedAt( LocalDateTime.now() );
        return this.productRepository.save( product );
    }

    @Override
    @Transactional
    public Product delete( String id )
    {
        Optional<Product> existed = this.productRepository.findById( id );
        if( existed.isEmpty() || !existed.get().getStatus() ) {
            throw new DocumentNotFountException( id, "Product", "ID" );
        }
        existed.get().setUpdatedAt( LocalDateTime.now() );
        existed.get().setStatus( false );

        return this.productRepository.save( existed.get() );
    }

    @Override
    @Transactional( readOnly = true )
    public int countAllDocuments()
    {
        return this.productRepository.countAllByStatusIsTrue();
    }
}