package com.store.catalog.app.domain.models;

import com.store.catalog.app.domain.entities.AuthorEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorDetailsDTO {

    private String id;

    private String firstName;

    private String lastName;

    private String description;

    public static AuthorDetailsDTO fromEntityToDTO(AuthorEntity authorEntity) {
        return AuthorDetailsDTO.builder()
                .id(authorEntity.getId().toString())
                .firstName(authorEntity.getFirstName())
                .lastName(authorEntity.getLastName())
                .description(authorEntity.getDescription())
                .build();
    }
}
