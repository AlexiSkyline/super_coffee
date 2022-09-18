package com.super_coffee.app.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Data @Document( "users" )
public class User {
    @Id
    private String _id;
    @NotNull @Size( min = 4, max = 16 )
    private String name;
    @Indexed( unique = true )
    @NotNull @Email
    private String email;
    @NotNull @Size( min = 6, max = 16 )
    private String password;
    @DBRef
    private Collection<Role> roles = new ArrayList<>();
    private boolean status = true;
    private LocalDateTime createdAt = LocalDateTime.now();
}