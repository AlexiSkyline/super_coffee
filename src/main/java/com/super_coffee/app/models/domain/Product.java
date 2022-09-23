package com.super_coffee.app.models.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data @Document( "products" )
public class Product
{
    @Id
    private String _id;
    private String name;
    private Double price;
    private String description;
    @DBRef
    private Category category;
    private Boolean available = true;
    private Boolean status = true;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
}