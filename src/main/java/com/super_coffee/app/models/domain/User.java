package com.super_coffee.app.models.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Data @Document( "users" )
public class User
{
    @Id
    private String _id;
    @NotNull @Size( min = 4, max = 16 )
    private String name;
    @NotNull @Email
    private String email;
    @NotNull @Size( min = 6, max = 16 )
    private String password;
    @DBRef
    private Collection<Role> roles = new ArrayList<>();
    private Boolean status = true;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public Collection<Role> addRole( Role role ) {
        boolean duplicateRole = this.roles.stream().anyMatch( rol -> rol.get_id().equals( role.get_id() ) );
        if( !duplicateRole ) {
            this.roles.add( role );
        }

        return this.roles;
    }
}