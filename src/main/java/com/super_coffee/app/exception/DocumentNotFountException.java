package com.super_coffee.app.exception;

import lombok.Getter;

public class DocumentNotFountException extends RuntimeException
{
    private final String term;
    private final String collectionName;
    @Getter
    private final String field;

    public DocumentNotFountException( String term, String collectionName,String field )
    {
        super( term );
        this.term = term;
        this.collectionName = collectionName;
        this.field = field;
    }

    @Override
    public String getMessage()
    {
        return String.format( "Not Found Any %s With The %s:'%s'", this.collectionName, this.field, this.term );
    }
}