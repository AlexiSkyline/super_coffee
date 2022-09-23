package com.super_coffee.app.service;

import java.util.List;

public interface ICrudRepository<T>
{
    T save( T document );
    List<T> findAll();
    T findById( String id );
    T update( String id, T category );
    T delete( String id );
    int countAllDocuments();
}