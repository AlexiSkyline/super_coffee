package com.super_coffee.app.repository;

import com.super_coffee.app.models.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface IProductRepository extends MongoRepository<Product, String>
{
    List<Product> findAllByStatusTrue();
    Optional<Product> findByName( String name );
    int countAllByStatusIsTrue();
}