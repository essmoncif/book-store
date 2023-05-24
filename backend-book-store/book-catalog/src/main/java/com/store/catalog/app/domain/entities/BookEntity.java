package com.store.catalog.app.domain.entities;

import com.store.catalog.app.domain.common.AuditableEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "Book")
public class BookEntity extends AuditableEntity<String> {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String title;

    private String shortDescription;

    private int edition;

    private LocalDateTime editionDate;

    private String path;

    @ManyToMany
    @JoinTable( name = "book_author",
                joinColumns = @JoinColumn(name = "book_id"),
                inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<AuthorEntity> authors;

    @ManyToMany
    @JoinTable( name = "book_genre",
                joinColumns = @JoinColumn(name = "book_id"),
                inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<GenreEntity> genres;

}
