package com.super_coffee.app.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor @Getter
public class ResponseAction
{
    private final String timeStamp;
    private final int httpCode;
    private final HttpStatus httpStatus;
    private final String message;
    private final Object data;
}