package com.super_coffee.app.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler
{
    public static ResponseEntity<Map<String, Object>> responseBuild( HttpStatus httpStatus, String message, int total, Object data )
    {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put( "timestamp", new Date().toString() );
        responseBody.put( "httpCode", httpStatus.value() );
        responseBody.put( "httpStatus", httpStatus );
        responseBody.put( "message", message );
        if( total > 0 ) {
            responseBody.put( "total", total );
        }
        responseBody.put( "data", data );

        return new ResponseEntity<>( responseBody, httpStatus );
    }
}