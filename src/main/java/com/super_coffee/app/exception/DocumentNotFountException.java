package com.super_coffee.app.exception;

import lombok.Getter;

public class DocumentNotFountException extends RuntimeException
{
    private final String id;
    private final String collectionName;
    @Getter
    private final String field;

    public DocumentNotFountException( String id, String collectionName,String field )
    {
        super( id );
        this.id = id;
        this.collectionName = collectionName;
        this.field = field;
    }

    @Override
    public String getMessage()
    {
        return String.format( "Not Found Any %s With The ID:'%s'", this.collectionName, this.id );
    }
}