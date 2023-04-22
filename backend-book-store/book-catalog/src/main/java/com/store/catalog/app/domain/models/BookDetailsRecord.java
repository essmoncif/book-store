package com.store.catalog.app.domain.models;

import com.store.catalog.app.domain.entities.BookEntity;

import java.util.List;
import java.util.stream.Collectors;

public record BookDetailsRecord(
        String uuid,
        String title,
        String description,
        int edition,
        List<AuthorDetailsRecord> authors
) {
    public static BookDetailsRecord bookEntityToBookDetailsRecord(final BookEntity book) {
        return new BookDetailsRecord(
                book.getId().toString(),
                book.getTitle(),
                book.getShortDescription(),
                book.getEdition(),
                book.getAuthors()
                        .stream()
                        .map(AuthorDetailsRecord::authorEntityToAuthorDetailsRecord)
                        .collect(Collectors.toList())
        );
    }
}
