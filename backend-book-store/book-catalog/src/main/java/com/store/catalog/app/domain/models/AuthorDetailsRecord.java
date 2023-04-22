package com.store.catalog.app.domain.models;

import com.store.catalog.app.domain.entities.AuthorEntity;

public record AuthorDetailsRecord(
        String uuid,
        String firstName,
        String lastName,
        String description
) {
    public static AuthorDetailsRecord authorEntityToAuthorDetailsRecord(final AuthorEntity author) {
        return new AuthorDetailsRecord(
                author.getId().toString(),
                author.getFirstName(),
                author.getLastName(),
                author.getDescription()
        );
    }
}
