package com.super_coffee.app.models;

import lombok.Data;

@Data
public class Error
{
    private final String message;
    private final String param;
    private final String location;
}