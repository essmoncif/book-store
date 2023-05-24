package com.store.catalog.app.domain.entities;

import com.store.catalog.app.domain.common.AuditableEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Entity(name = "Author")
public class AuthorEntity extends AuditableEntity<String> {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String firstName;

    private String lastName;

    private String description;

    @ManyToMany(mappedBy = "authors")
    private List<BookEntity> books;

}
