package com.super_coffee.app.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document( "roles" )
@NoArgsConstructor
public class Role {
    @Id
    private String _id;
    @NotNull( message = "is Required" )
    private String description;

    public Role( String description ) {
        this.description = description;
    }
}