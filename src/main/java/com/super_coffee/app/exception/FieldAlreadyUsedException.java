package com.super_coffee.app.exception;

import lombok.Getter;

@Getter
public class FieldAlreadyUsedException extends RuntimeException
{
    private final String fieldName;
    private final String documentName;

    public FieldAlreadyUsedException( String fieldName, String documentName )
    {
        super( fieldName );
        this.fieldName = fieldName;
        this.documentName = documentName;
    }

    @Override
    public String getMessage()
    {
        return String.format( "Field '%s' is already being used with another %s.", this.fieldName, this.documentName );
    }
}