package com.super_coffee.app.models.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data @Document( "roles" ) @NoArgsConstructor
public class Role
{
    @Id
    private String _id;
    @NotNull( message = "is Required" )
    private String description;
    private Boolean status = true;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public Role( String description )
    {
        this.description = description;
    }
}