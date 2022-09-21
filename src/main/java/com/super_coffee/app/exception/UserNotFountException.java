package com.super_coffee.app.exception;

public class UserNotFountException extends RuntimeException
{
    private final String user;

    public UserNotFountException( String user )
    {
        super( user );
        this.user = user;
    }

    @Override
    public String getMessage()
    {
        return String.format( "Not Found Any User With The ID:'%s'", this.user );
    }
}