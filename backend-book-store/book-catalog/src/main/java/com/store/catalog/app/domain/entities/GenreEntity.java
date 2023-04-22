package com.store.catalog.app.domain.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Genre")
public class GenreEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String genreName;

    @ManyToMany(mappedBy = "genres")
    private Set<BookEntity> books = new HashSet<>();
}
