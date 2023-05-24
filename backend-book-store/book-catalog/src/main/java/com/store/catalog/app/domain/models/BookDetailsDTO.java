package com.store.catalog.app.domain.models;


import com.store.catalog.app.domain.entities.BookEntity;
import com.store.catalog.app.domain.entities.GenreEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDetailsDTO {

    private String id;

    private String title;

    private String shortDescription;

    private int edition;

    private LocalDateTime editionDate;

    private List<String> genres;

    private String path;

    private List<AuthorDetailsDTO> authors;

    public static BookDetailsDTO fromEntityToDTO(BookEntity bookEntity) {
        return BookDetailsDTO.builder()
                .id(bookEntity.getId().toString())
                .title(bookEntity.getTitle())
                .shortDescription(bookEntity.getShortDescription())
                .edition(bookEntity.getEdition())
                .editionDate(bookEntity.getEditionDate())
                .path(bookEntity.getPath())
                .genres(bookEntity
                        .getGenres()
                        .stream()
                        .map(GenreEntity::getGenreName)
                        .toList())
                .authors(bookEntity
                        .getAuthors()
                        .stream()
                        .map(AuthorDetailsDTO::fromEntityToDTO)
                        .toList())
                .build();
    }

}
