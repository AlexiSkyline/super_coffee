package com.super_coffee.app.exception;

import lombok.Getter;

@Getter
public class FieldAlreadyUsedException extends RuntimeException
{
    private final String fieldName;

    public FieldAlreadyUsedException( String fieldName )
    {
        super( fieldName );
        this.fieldName = fieldName;
    }

    @Override
    public String getMessage()
    {
        return String.format( "Field '%s' is already being used with another account.", this.fieldName );
    }
}