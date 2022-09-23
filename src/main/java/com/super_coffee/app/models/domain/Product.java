package com.super_coffee.app.models.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data @Document( "products" )
public class Product
{
    @Id
    private String _id;
    @NotNull @Size( min = 4, max = 16 )
    private String name;
    @NotNull
    private Double price;
    @NotNull @Size( min = 5, max = 25 )
    private String description;
    @DBRef
    private Category category;
    private Boolean available = true;
    private Boolean status = true;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
}