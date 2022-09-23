package com.super_coffee.app.response;

import com.super_coffee.app.models.response.ResponseAction;
import com.super_coffee.app.models.response.ResponseGet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public class ResponseHandler
{
    public static ResponseEntity<ResponseGet> responseBuild( HttpStatus httpStatus, String message, int total, Object data )
    {
        ResponseGet responseBody = new ResponseGet( new Date().toString(), httpStatus.value(), httpStatus, message, total, data );
        return new ResponseEntity<>( responseBody, httpStatus );
    }

    public static ResponseEntity<ResponseAction> responseBuild( HttpStatus httpStatus, String message, Object data )
    {
        ResponseAction responseBody = new ResponseAction( new Date().toString(), httpStatus.value(), httpStatus, message, data );
        return new ResponseEntity<>( responseBody, httpStatus );
    }
}