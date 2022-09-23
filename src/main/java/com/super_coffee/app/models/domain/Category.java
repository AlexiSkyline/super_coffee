package com.super_coffee.app.models.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data @Document( "categories" )
public class Category
{
    @Id
    private String _id;
    @NotNull
    @Size( min = 4, max = 16 )
    private String name;
    private boolean status = true;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
}